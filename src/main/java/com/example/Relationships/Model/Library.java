package com.example.Relationships.Model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Library {

    @Id
    private int id;
    private String name;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @JsonBackReference
    @OneToMany(mappedBy = "library")
    Set<Book> books = new HashSet<>(0);

}
