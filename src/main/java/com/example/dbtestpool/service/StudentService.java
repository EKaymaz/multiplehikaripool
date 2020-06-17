package com.example.dbtestpool.service;

import com.example.dbtestpool.model.Student;
import com.example.dbtestpool.repository.read.StudentRepositoryRead;
import com.example.dbtestpool.repository.write.StudentRepositoryWrite;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private final StudentRepositoryRead studentRepositoryRead;
    private final StudentRepositoryWrite studentRepositoryWrite;

    public StudentService(StudentRepositoryRead studentRepository, StudentRepositoryWrite studentRepositoryWrite) {
        this.studentRepositoryRead = studentRepository;
        this.studentRepositoryWrite = studentRepositoryWrite;
    }

    @Transactional("writingTransactionManager")
    public Student save(Student student) {
        return studentRepositoryWrite.save(student);
    }

    public Student getStudentById(int id) {
        return studentRepositoryRead.findById(id).orElseThrow(RuntimeException::new);
    }

}
