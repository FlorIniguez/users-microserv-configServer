package com.api.users.exceptions;



import lombok.Getter;
import lombok.Setter;

@Getter@Setter
//Campo no encontradp
public class ResourcedNotFoundException extends IllegalArgumentException {
    private String resourceName;
    private String fieldName;
    private Long value;

    public ResourcedNotFoundException(String resourceName, String fieldName, Long value) {
        super(String.format("%s not found: %s,'%s '",resourceName,fieldName,value));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.value = value;
    }
}
