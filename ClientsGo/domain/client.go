package domain

type Clients struct {
	SharedKey  string `gorm:"primaryKey;column:shared_key" json:"sharedKey"`
	BusinessID string `json:"businessId"`
	Email      string `json:"email"`
}

func (client *Clients) TableName() string {
	return "clients"
}
