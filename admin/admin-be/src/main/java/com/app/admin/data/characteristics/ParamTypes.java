package com.app.admin.data.characteristics;

import java.util.HashMap;
import java.util.Map;

public enum ParamTypes {
    FLOAT_TYPE(1),
    INTEGER_TYPE(2),
    STRING_TYPE(3);

    private static final Map<Integer, ParamTypes> MAP = new HashMap<Integer, ParamTypes>();
    static {
        for (ParamTypes type : values()) {
            MAP.put(type.getValue(), type);
        }
    }

    private final Integer id;

    ParamTypes(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return id;
    }

    public static ParamTypes getTypeById(Integer value) {
        return MAP.get(value);
    }
}
