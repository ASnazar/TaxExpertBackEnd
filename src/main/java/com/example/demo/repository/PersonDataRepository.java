package com.example.demo.repository;

import com.example.demo.entity.PersonData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDataRepository extends JpaRepository<PersonData, Long> {

}
