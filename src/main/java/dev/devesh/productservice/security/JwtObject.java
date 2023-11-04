package dev.devesh.productservice.security;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class JwtObject {

    private String email;
    private long userId;
    private Date createdAt;
    private Date expiryAt;
    private List<Role> roles=new ArrayList<>();

}
