package com.ru.prac5.repository;

import com.ru.prac5.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    public List<Client> findAllByOrderByIdAsc();
}
