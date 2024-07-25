package main

import (
	"ClientsGo/infrastructure/repository"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
	"log"

	"ClientsGo/application"
)

func main() {
	dsn := "host=localhost user=postgres password=secure_pass_here dbname=exampledb port=5432 sslmode=disable TimeZone=America/Lima"
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		log.Fatalf("No se pudo conectar a la base de datos: %v", err)
	}
	/*db.Exec("DROP TABLE IF EXISTS orders")
	db.Exec("DROP TABLE IF EXISTS clients")
	db.Exec("DROP TABLE IF EXISTS product")
	*/
	//err = db.AutoMigrate(&domain.Clients{}, &domain.Product{}, &domain.Orders{})
	if err != nil {
		log.Fatalf("No se pudo migrar el modelo: %v", err)
	}
	clientRepo := repository.NewClientRepository(db)
	clientService := application.NewClientService(clientRepo)

	router := SetupRouter(clientService)

	router.Run(":8090")
}
