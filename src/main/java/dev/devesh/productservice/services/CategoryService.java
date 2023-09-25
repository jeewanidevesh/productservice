package dev.devesh.productservice.services;

import dev.devesh.productservice.models.Category;

import java.util.List;

public interface CategoryService {

    Category getCategory(String uuid);
    List<String> getProductTitles(List<String> categoryUUIDs);
}
