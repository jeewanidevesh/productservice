package dev.devesh.productservice.repositories;

import dev.devesh.productservice.models.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, UUID> {
    Product findByTitleEquals(String title);

    Product findByTitleEqualsAndPrice_Price(String title,double price );

    Product findByTitleEqualsAndPrice_PriceOrderByPrice_price(String title,double price );

    List<Product> findAllByPrice_Currency(String currency);

    List<Product> findDistinctByPrice_Currency(String currency);

    void delete(Product entity);

    long countAllByPrice_Currency(String currency);

    long countDistinctByPrice_Currency(String currency);

    List<Product> findAllByTitleLike(String titleRegex);

    List<Product> readAllByTitleLike(String titleRegex);

//    @Query(value = "select * from product where title = :title1",nativeQuery = true)
//@Query(value = "select * from product.id join product_orders on product.id = product_orders.product_id where title = :title1",nativeQuery = true)
//@Query(value = "select * from product.title join product_orders on product.id = product_orders.product_id where title = :title1",nativeQuery = true)
//@Query(value = "select * from product.id,product.category join product_orders on product.id = product_orders.product_id where title = :title1",nativeQuery = true)
//    @Query(value = "select * from product join product_orders on product.id = product_orders.product_id where title = :title1",nativeQuery = true)
    @Query(value = CustomQueries.FIND_ALL_BY_TITLE,nativeQuery = true)
    List<Product> findAllByTitle(String title1);

//    @Query("select Product from Product where Product.title= :title1")
//    @Query("select Product from Product where Product.price.currency=:currency and Product .title = :title1")
//    List<Product> readAllByTitle(String title1,String currency);
}
