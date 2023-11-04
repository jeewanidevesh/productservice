package dev.devesh.productservice.security;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TokenValidator {

    private RestTemplateBuilder restTemplateBuilder;

    public TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }


    /**
     * calls user service to validate token.
     * If token is not valid ,optional is empty.
     * Else optional contains all of the data in payload.
     */
    public Optional<JwtObject> validateToken(String token){
        RestTemplate restTemplate=restTemplateBuilder.build();

        return Optional.empty();

    }
}
