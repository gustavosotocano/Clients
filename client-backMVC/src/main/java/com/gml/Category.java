package com.gml;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "categoriesName")
    private Set<Notification> notifications = new LinkedHashSet<>();

}