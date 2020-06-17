package com.example.dbtestpool.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Student {

    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private int age;

}
