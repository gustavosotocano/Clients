package domain

type Orders struct {
	OrderID   string  `gorm:"primaryKey;column:order_id" json:"order_id"`
	SharedKey string  `gorm:"column:shared_key" json:"shared_key"`
	ProductID string  `gorm:"column:product_id" json:"product_id"`
	Client    Clients `gorm:"foreignKey:SharedKey;references:SharedKey" json:"client"`
	Product   Product `gorm:"foreignKey:ProductID;references:ProductID" json:"product"`
}

func (orders *Orders) TableName() string {
	return "orders"
}
