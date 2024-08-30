package com.hsh.demo.repository;

import com.hsh.demo.pojo.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
     public Product findById(int id);

     @Modifying
     @Query("update Product set status = false where id = ?1")
     public void setStatusProduct(int id);
}
