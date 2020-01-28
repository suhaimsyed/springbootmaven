package com.demo.spring.demo.repository;

import com.demo.spring.demo.beans.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("SELECT c FROM Cart c where c.code = :code")
    Optional<Cart> findCartByCode(@Param("code") String code);

}
