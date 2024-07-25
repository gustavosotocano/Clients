package domain

type ClientRepository interface {
	Create(cliente *Clients) error
	GetAll() ([]Clients, error)
	GetByID(id string) (*Clients, error)
	Update(cliente *Clients) error
	Delete(id string) error
}
