package com.gas.adapter;


import com.gas.dominio.Client;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Locale;
/*import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;*/


@Entity
@Table(name="client")
/*@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor*/
public class ClientEntity extends PanacheEntityBase {

    @Id
    @Column(name = "shared_key")
    public String sharedKey;
    @Column(name = "business_id")
    public String businessId;
    public String email;
    public String phone;


    public String getSharedKey() {
        return sharedKey;
    }

    public void setSharedKey(String sharedKey) {
        this.sharedKey = sharedKey;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public com.gas.dominio.Client toDomain() {
        com.gas.dominio.Client client = new com.gas.dominio.Client();
        client.setSharedKey(this.sharedKey);
        client.setBusinessId(this.businessId);
        client.setEmail(this.email);
        return client;
    }

    public static ClientEntity fromDomain(Client client) {
        ClientEntity entity = new ClientEntity();
        String[] names=client.getBusinessId().split(" ");
        String lastName = "";
        if (names.length == 1) {
            lastName = names[0];
        } else if (names.length == 2) {
            lastName = names[1];
        } else if (names.length > 2) {
            lastName = names[2];
        }

        String initial = names[0].substring(0, 1);

        entity.setSharedKey((initial+lastName).toLowerCase(Locale.ROOT));
entity.setPhone(client.getPhone());

        entity.setBusinessId(client.getBusinessId());
        entity.setEmail(client.getEmail());
        return entity;
    }


}


