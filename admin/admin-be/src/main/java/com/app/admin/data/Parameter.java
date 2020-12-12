package com.app.admin.data;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@Entity(name="parameters")
@NoArgsConstructor
@AllArgsConstructor
public class Parameter {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    private Integer type;

    public String getTypeDescription() {
        return ParamTypes.getTypeById(type).name();
    }
}
