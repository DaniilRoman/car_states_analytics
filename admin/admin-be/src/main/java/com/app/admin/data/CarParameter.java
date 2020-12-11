package com.app.admin.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity(name="car_parameters")
@NoArgsConstructor
@AllArgsConstructor
public class CarParameter implements Serializable {

    @Id
    private UUID car_id;

    @Id
    private UUID parameter_id;

    @ManyToOne
    @JoinColumn(name="parameter_id")
    private Parameter parameter;

    private String val;
}
