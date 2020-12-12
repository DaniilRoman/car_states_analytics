package com.app.admin.data;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name="car_parameters")
@NoArgsConstructor
@AllArgsConstructor
public class CarParameter implements Serializable {

    @EmbeddedId
    CarParameterKey id;

    @ManyToOne
    @MapsId("carId")
    @JoinColumn(name="car_id")
    private Car car;

    @ManyToOne
    @MapsId("parameterId")
    @JoinColumn(name="parameter_id")
    private Parameter parameter;

    private String val;

    public CarParameter(CarParameterKey id, String value) {
        this.id = id;
        this.val = value;
    }
}
