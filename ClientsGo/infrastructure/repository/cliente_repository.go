package repository

import (
	"ClientsGo/domain"
	"gorm.io/gorm"
)

type ClientRepository struct {
	db *gorm.DB
}

func NewClientRepository(db *gorm.DB) *ClientRepository {
	return &ClientRepository{db: db}
}

func (r *ClientRepository) Create(cliente *domain.Clients) error {
	return r.db.Create(cliente).Error
}

func (r *ClientRepository) GetAll() ([]domain.Clients, error) {
	var clientes []domain.Clients
	err := r.db.Preload("Orders").Find(&clientes).Error
	return clientes, err
}

func (r *ClientRepository) GetByID(id string) (*domain.Clients, error) {
	var cliente domain.Clients
	//result := r.db.First(&cliente, map[string]interface{}{"shared_key": id}).Error
	result := r.db.First(&cliente, "shared_key=?", id)
	return &cliente, result.Error
}

func (r *ClientRepository) Update(cliente *domain.Clients) error {
	return r.db.Save(cliente).Error
}

func (r *ClientRepository) Delete(id string) error {
	return r.db.Delete(&domain.Clients{}, id).Error
}
