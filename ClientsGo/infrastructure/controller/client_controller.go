// infrastructure/controller/client_controller.go
package controller

import (
	"ClientsGo/application"
	"ClientsGo/domain"
	"net/http"

	"github.com/gin-gonic/gin"
)

type ClientController struct {
	service *application.ClientService
}

func NewClientController(service *application.ClientService) *ClientController {
	return &ClientController{service: service}
}

func (c *ClientController) GetClient(ctx *gin.Context) {
	sharedKey := ctx.Param("id")
	client, err := c.service.GetClient(sharedKey)
	if err != nil {
		ctx.JSON(http.StatusNotFound, gin.H{"error": err.Error()})
		return
	}
	ctx.JSON(http.StatusOK, client)
}

func (c *ClientController) CreateClient(ctx *gin.Context) {
	var client domain.Clients
	if err := ctx.ShouldBindJSON(&client); err != nil {
		ctx.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	if err := c.service.CreateClient(&client); err != nil {
		ctx.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}
	ctx.JSON(http.StatusCreated, client)
}
func (c *ClientController) UpdateClient(ctx *gin.Context) {
	id := ctx.Param("id")
	var cliente domain.Clients
	if err := ctx.ShouldBindJSON(&cliente); err != nil {
		ctx.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	cliente.SharedKey = id
	err := c.service.UpdateClient(&cliente)
	if err != nil {
		ctx.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}
	ctx.JSON(http.StatusOK, cliente)
}

func (c *ClientController) DeleteClient(ctx *gin.Context) {
	id := ctx.Param("id")
	err := c.service.DeleteClient(id)
	if err != nil {
		ctx.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}
	ctx.JSON(http.StatusOK, gin.H{"message": "Cliente eliminado"})
}

// Similarly, add methods for UpdateClient and DeleteClient
