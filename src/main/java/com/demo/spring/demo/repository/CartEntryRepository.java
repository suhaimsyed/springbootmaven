package com.demo.spring.demo.repository;


import com.demo.spring.demo.beans.Cart;
import com.demo.spring.demo.beans.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartEntryRepository extends JpaRepository<Entry,Long> {
}
