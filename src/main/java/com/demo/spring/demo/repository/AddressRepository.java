package com.demo.spring.demo.repository;


import com.demo.spring.demo.beans.Address;
import com.demo.spring.demo.beans.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
