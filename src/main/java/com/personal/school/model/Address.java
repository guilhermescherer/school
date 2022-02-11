package com.personal.school.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String zipCode;
    private String city;
    private String country;
    @OneToOne(mappedBy = "address")
    private People people;

    public Address() { }


}
