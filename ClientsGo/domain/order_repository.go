package domain

type OrderRepository interface {
	GetByID(orderID string) (*Orders, error)
	Create(order *Orders) error
	Update(order *Orders) error
	Delete(orderID string) error
}
