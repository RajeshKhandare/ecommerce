package com.demo.repository;

import com.demo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    // Custom finder method to search by item name
    List<Item> findByNameContainingIgnoreCase(String name);
}
