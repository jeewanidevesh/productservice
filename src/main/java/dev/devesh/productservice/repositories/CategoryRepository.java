package dev.devesh.productservice.repositories;

import dev.devesh.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository
extends JpaRepository<Category, UUID> {

    @Override
    Optional<Category> findById(UUID uuid);
}
