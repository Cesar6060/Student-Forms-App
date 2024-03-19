package com.swe6673.studentmanagementsys.serviceimplementation;

import com.swe6673.studentmanagementsys.entity.Student;
import com.swe6673.studentmanagementsys.repository.StudentRepository;
import com.swe6673.studentmanagementsys.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

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
            return null;
      }

      @Override
      public Student getStudentById(Long id) {
            return null;
      }

      @Override
      public List<Student> searchStudents(String keyword) {
            // TO DO: Implement the search logic here
            return null;
      }
      private boolean isValidEmail(String email) {
            if (email == null) {
                  return false;
            }
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            return email.matches(emailRegex);
      }

}
