package dev.devesh.productservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel {

    @Id
    private Long id;
}
