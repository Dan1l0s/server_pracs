package com.ru.prac5.repository;

import com.ru.prac5.model.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Integer> {
    public List<Telephone> findAllByOrderByIdAsc();
}
