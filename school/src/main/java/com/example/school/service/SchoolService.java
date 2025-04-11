package com.example.school.service;

import com.example.school.dto.FullSchoolResponse;
import com.example.school.entity.School;
import com.example.school.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class SchoolService {
    final SchoolRepository schoolRepository;
    private final StudentService studentService;

//    public SchoolService(SchoolRepository schoolRepository, StudentService studentService) {
//        this.schoolRepository = schoolRepository;
//        this.studentService = studentService;
//    }

    public void saveSchool(School school){
        schoolRepository.save(school);
    }

    public List<School> findAllSchools(){

        List<School> schools = schoolRepository.findAll();
        return schools;
    }

    public FullSchoolResponse findStudentsBySchoolId(Integer schoolId) {
        var school = schoolRepository.findById(schoolId)
                        .orElse(
                                School.builder()
                                        .name("NOT_FOUND")
                                        .email("NOT_FOUND")
                                        .build()
                        );
        var students = studentService.findAllStudentsBySchool(schoolId);
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .studentList(students).build();
    }
}
