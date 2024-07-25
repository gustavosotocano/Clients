package domain

type ProductRepository interface {
	GetByID(productID string) (*Product, error)
	Create(product *Product) error
	Update(product *Product) error
	Delete(productID string) error
}
