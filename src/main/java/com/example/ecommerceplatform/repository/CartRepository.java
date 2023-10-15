package com.example.ecommerceplatform.repository;

import com.example.ecommerceplatform.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    // You can add custom query methods here if needed
}
