package com.swe6673.studentmanagementsys.serviceimplementation;

import com.swe6673.studentmanagementsys.entity.Student;
import com.swe6673.studentmanagementsys.repository.StudentRepository;
import com.swe6673.studentmanagementsys.service.StudentService;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

      private StudentRepository studentRepository;

      public StudentServiceImpl(StudentRepository studentRepository) {
            super();
            this.studentRepository = studentRepository;
      }

      @Override
      public Student saveStudent(Student student) {
            if (!isValidEmail(student.getEmail())) {
                  throw new RuntimeException("Invalid email");
            }
            return studentRepository.save(student);
      }

      @Override
      public Student updateStudent(Student student) {
            if (student.getId() != null && studentRepository.existsById(student.getId())) {
                  return studentRepository.save(student);
            } else {
                  throw new RuntimeException("Student not found");
            }
      }

      @Override
      public void deleteStudentById(Long id) {
            if (id != null && studentRepository.existsById(id)) {
                  studentRepository.deleteById(id);
            } else {
                  throw new RuntimeException("Student not found");
            }
      }

      @Override
      public List<Student> getAllStudents() {
          return studentRepository.findAll();
      }

      @Override
      public Student getStudentById(Long id) {
          Student student = studentRepository.getReferenceById(id);
          if (student != null) {
    			  return student;
    	  }
          else {
        	  throw new RuntimeException("Student ID does not exist");
          }
      }

      @Override
      public List<Student> searchStudents(String keyword) {
    	  if (keyword == null) {
    		  throw new IllegalArgumentException("Query cannot be null");
    	  }
    	  return studentRepository.findByFirstNameContainingOrLastNameContaining("keyword","keyword");
      }
      
      private boolean isValidEmail(String email) {
            if (email == null) {
                  return false;
            }
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            return email.matches(emailRegex);
      }

}
