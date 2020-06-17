package com.example.dbtestpool.repository.write;

import com.example.dbtestpool.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositoryWrite extends JpaRepository<Student, Integer> {
}
