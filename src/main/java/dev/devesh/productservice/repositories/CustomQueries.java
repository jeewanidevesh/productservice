package dev.devesh.productservice.repositories;

public interface CustomQueries {

    String FIND_ALL_BY_TITLE="select * from product join product_orders on product.id = product_orders.product_id where title = :title1";
    String FIND_ALL_PRODUCT_BY_CATEGORY ="SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.category LEFT JOIN FETCH p.price where p.category.name=:categoryName" ;
    String GET_ALL_PRODUCT_CATEGORY="Select distinct c.name from product p left join category c on p.category = c.id";
}
