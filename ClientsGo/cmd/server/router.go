package main

import (
	"ClientsGo/application"
	"ClientsGo/infrastructure/controller"

	"github.com/gin-gonic/gin"
)

func SetupRouter(clienteService *application.ClientService) *gin.Engine {
	router := gin.Default()

	clienteHandler := controller.NewClientController(clienteService)
	//productHandler := NewProductHandler(productService)
	//	orderHandler := NewOrderHandler(orderService)

	router.GET("/v1/clients", clienteHandler.GetClient)
	router.GET("/v1/clients/:id", clienteHandler.GetClient)
	router.POST("/v1/clients", clienteHandler.CreateClient)
	router.PUT("/v1/clients/:shared_key", clienteHandler.UpdateClient)
	router.DELETE("/v1/clients/:shared_key", clienteHandler.DeleteClient)

	/*	router.GET("/products", productHandler.GetProducts)
		router.GET("/products/:product_id", productHandler.GetProduct)
		router.POST("/products", productHandler.CreateProduct)
		router.PUT("/products/:product_id", productHandler.UpdateProduct)
		router.DELETE("/products/:product_id", productHandler.DeleteProduct)*/

	/*	router.GET("/orders", orderHandler.GetOrders)
		router.GET("/orders/:order_id", orderHandler.GetOrder)
		router.POST("/orders", orderHandler.CreateOrder)
		router.PUT("/orders/:order_id", orderHandler.UpdateOrder)
		router.DELETE("/orders/:order_id", orderHandler.DeleteOrder)*/

	return router
}
