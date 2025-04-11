package com.example.school.service;

import com.example.school.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final WebClient.Builder webClient;

    @Value("${student.service.base.url}")
    private String baseUrl;

    public List<Student> findAllStudentsBySchool(Integer schoolId) {
        return webClient.baseUrl(baseUrl).build().get()
                .uri("/school/{schoolId}", schoolId)
                .retrieve().bodyToFlux(Student.class)
                .collectList().block();
    }

}
