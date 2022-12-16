package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    private String name;

    @Length(min = 5, max = 200)
    @Column(length = 200)
    @Email
    @NotEmpty
    @NotNull
    private String email;

    @Length(min = 1, max = 500)
    @Column(length = 500)
    @NotEmpty
    @NotNull
    private String password;

}
