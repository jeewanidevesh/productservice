package dev.devesh.productservice.dtos;

import dev.devesh.productservice.security.JwtObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request<T> {

    T userPayload;
    JwtObject authPayload;
}
