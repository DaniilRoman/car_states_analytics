package com.app.admin.data;

import com.app.admin.data.user.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity(name="cars")
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String brand;

    private String model;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private Account owner;

    @OneToMany(cascade=ALL, mappedBy="car_id")
    Set<CarParameter> parameters;
}
