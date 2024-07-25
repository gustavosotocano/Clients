package domain

type Product struct {
	ProductID string `gorm:"primaryKey;column:product_id" json:"product_id"`
	Name      string `json:"name"`
	Price     int    `json:"price"`
}

func (product *Product) TableName() string {
	return "product"
}
