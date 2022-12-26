package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Customer> customers;


}
