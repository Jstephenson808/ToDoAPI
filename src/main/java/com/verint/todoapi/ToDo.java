package com.verint.todoapi;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/*
 what is the best approach with the constructors?
 also had to remove final fields
 "no-args constructor required by JPA spec"
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class ToDo {

    @Id
    private Long ID;

    @Column(nullable = false)
    private String name;
}
