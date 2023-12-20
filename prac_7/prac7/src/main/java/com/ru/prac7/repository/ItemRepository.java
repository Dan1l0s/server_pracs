package com.ru.prac7.repository;

import com.ru.prac7.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    public List<Item> findAllByOrderByIdAsc();
}
