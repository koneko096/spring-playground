package com.gdn.afrizal.playground.spring.web.controller;

import com.gdn.afrizal.playground.spring.data.repository.EnrollmentRepository;
import com.gdn.afrizal.playground.spring.dto.StudentEnrollment;
import com.gdn.afrizal.playground.spring.model.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @GetMapping("/enrollment")
    public List<Enrollment> getEnrollments() {
        return enrollmentRepository.findAll();
//        return customerDAO.list();
    }

    @GetMapping("/enrollment/{courseId}")
    public ResponseEntity getEnrollment(@PathVariable("courseId") Long courseId) {
//        Customer customer = customerDAO.get(id);
//        if (customer == null) {
//            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity(new StudentEnrollment(), HttpStatus.OK);
    }

//    @PostMapping(value = "/course/register")
//    public ResponseEntity createCustomer(@RequestBody  customer) {
//
//        customerDAO.create(customer);
//
//        return new ResponseEntity(customer, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/customers/{id}")
//    public ResponseEntity deleteCustomer(@PathVariable Long id) {
//
//        if (null == customerDAO.delete(id)) {
//            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity(id, HttpStatus.OK);
//
//    }
//
//    @PutMapping("/customers/{id}")
//    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
//
//        customer = customerDAO.update(id, customer);
//
//        if (null == customer) {
//            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity(customer, HttpStatus.OK);
//    }
}