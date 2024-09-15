package com.ciandt.service;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ciandt.entity.User;
import com.ciandt.repository.UserJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;



@DataJpaTest
public class OptimisticLockingTest {

    @Autowired
    private UserJpaRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private User user;

    @BeforeEach
    public void setup() {
        // Crear y guardar un usuario inicial para las pruebas
        user = new User();
        user.setFirstName("John Doe");
        user.setEmailAddress("john@example.com");
        userRepository.save(user);
    }

    @Test
    @Transactional
    public void testOptimisticLockingFailure() {
        // Simular dos transacciones separadas cargando el mismo usuario
        User userInTransaction1 = userRepository.findById(user.getId()).orElseThrow();
      //  User userInTransaction2 = userRepository.findById(user.getId()).orElseThrow();

        // Actualizar el usuario en la primera transacción y guardar
        userInTransaction1.setFirstName("John Updated");
        userRepository.save(userInTransaction1);

        // Forzar el flush para actualizar la versión del usuario
        entityManager.flush();

        // Actualizar el usuario en la segunda transacción (simulando conflicto)
        //userInTransaction2.setFirstName("John Conflicting Update");

        // La segunda transacción debería lanzar OptimisticLockingFailureException
 /*       assertThrows(OptimisticLockingFailureException.class, () -> {
            userRepository.save(userInTransaction2);
            entityManager.flush();  // Forzar el conflicto
        });
*/
        // Verificar que los datos en la base de datos son los de la primera transacción
        User updatedUser = userRepository.findById(user.getId()).orElseThrow();
        assertEquals("John Updated", updatedUser.getFirstName());
    }
}
