package com.app.admin.data;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarParameterKey implements Serializable {

    @Column(name = "car_id")
    UUID carId;

    @Column(name = "parameter_id")
    UUID parameterId;
}
