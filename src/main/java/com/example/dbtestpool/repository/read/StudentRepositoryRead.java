package com.example.dbtestpool.repository.read;

import com.example.dbtestpool.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositoryRead extends JpaRepository<Student, Integer> {
}
