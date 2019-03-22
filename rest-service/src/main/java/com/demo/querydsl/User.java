package com.demo.querydsl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Long age;

    @Column(name = "email", nullable = false)
    private String email;

    @Builder
    public User(String name, Long age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
