// application/client_service.go
package application

import (
	"ClientsGo/domain"
)

type ClientService struct {
	repo domain.ClientRepository
}

func NewClientService(repo domain.ClientRepository) *ClientService {
	return &ClientService{repo: repo}
}

func (s *ClientService) GetClient(sharedKey string) (*domain.Clients, error) {
	return s.repo.GetByID(sharedKey)
}

func (s *ClientService) CreateClient(client *domain.Clients) error {
	return s.repo.Create(client)
}

func (s *ClientService) UpdateClient(client *domain.Clients) error {
	return s.repo.Update(client)
}

func (s *ClientService) DeleteClient(sharedKey string) error {
	return s.repo.Delete(sharedKey)
}
func (s *ClientService) GetClientes() ([]domain.Clients, error) {
	return s.repo.GetAll()
}
