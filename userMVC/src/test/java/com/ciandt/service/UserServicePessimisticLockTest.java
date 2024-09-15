package com.ciandt.service;


import com.ciandt.entity.User;
import com.ciandt.repository.UserJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;



@DataJpaTest
public class UserServicePessimisticLockTest {

    @Autowired
    private UserJpaRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testPessimisticLocking() throws InterruptedException {
        // Crear un nuevo usuario en la base de datos
        User user = new User();
        user.setFirstName("John Doe");
        user.setEmailAddress("john.doe@example.com");
        user = userRepository.save(user);

        // Simular dos transacciones que intentan obtener el mismo usuario con bloqueo pesimista

        // Primera transacción
        User finalUser = user;
        Runnable transaction1 = () -> {
            entityManager.lock(finalUser, LockModeType.PESSIMISTIC_WRITE); // Bloquear el registro para escritura
            User lockedUser = userRepository.findById(finalUser.getId()).orElseThrow();
            lockedUser.setFirstName("John Updated");
            userRepository.saveAndFlush(lockedUser);

            // Simular un retraso en la primera transacción
            try {
                Thread.sleep(2000); // Simula un retraso de 2 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            entityManager.flush(); // Forzar el guardado de los cambios y la liberación del bloqueo
        };

        // Segunda transacción que intenta modificar el mismo usuario mientras está bloqueado
        User finalUser1 = user;
        Runnable transaction2 = () -> {
            try {
                // Intentar adquirir el bloqueo mientras está bloqueado por la primera transacción
                User lockedUser = entityManager.find(User.class, finalUser1.getId(), LockModeType.PESSIMISTIC_WRITE);
                lockedUser.setFirstName("John Another Update");
                userRepository.saveAndFlush(lockedUser);
            } catch (PessimisticLockingFailureException e) {
                System.out.println("No se pudo adquirir el bloqueo: " + e.getMessage());
            }
        };

        // Ejecutar ambas transacciones en hilos separados
        Thread thread1 = new Thread(transaction1);
        Thread thread2 = new Thread(transaction2);

        thread1.start();
        Thread.sleep(100); // Asegurarse de que la primera transacción adquiera el bloqueo antes que la segunda
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
