package com.getgoods.productservice.Repository;

import com.getgoods.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    public Optional<Product> findByName(String name);
    public List<Product> findByCategoryId(Long categoryId);


    public List<Product> findAllByOrderByPriceAsc();
    public List<Product> findAllByOrderByPriceDesc();

    public List<Product> findAllByOrderByExpDateAsc();
    public List<Product> findAllByOrderByExpDateDesc();

    @Query(value = "SELECT * FROM Product WHERE price BETWEEN :min AND :max", nativeQuery = true)
    List<Product> findByRange(@Param("min") int min, @Param("max") int max);



}
