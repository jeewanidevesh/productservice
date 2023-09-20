package dev.devesh.productservice.repositories;

import dev.devesh.productservice.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository
        extends JpaRepository<Price,Long> {
}
