package dev.tal.kin.core.variables;

import lombok.Getter;
import lombok.Setter;

/**
 * @proje KinLanguage
 * @gelistirici talyazilim
 * @tarih 8/17/2025
 */
@Getter
public class IVariable {

    public enum Type {
        INTEGER, DOUBLE, FLOAT;
    }

    private Type type;
    private String name;
    @Setter
    private Object value;

    public IVariable(Type type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
