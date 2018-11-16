package com.nazaruk.medApteka.controller;


import com.nazaruk.medApteka.exeption.ResourceNotFoundException;
import com.nazaruk.medApteka.model.Doctor;
import com.nazaruk.medApteka.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @PostMapping("/doctors")
    public Doctor createDoctor(@Valid @RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @GetMapping("/doctors/{id}")
    public Doctor getDoctorById(@PathVariable(value = "id") Integer doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));
    }

    @PutMapping("/doctors/{id}")
    public Doctor updateDoctor(@PathVariable(value = "id") Integer doctorId,
                           @Valid @RequestBody Doctor doctorDetails) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", doctorId));

        doctor.setName(doctorDetails.getName());
        doctor.setSurname(doctorDetails.getSurname());
        doctor.setMidname(doctorDetails.getMidname());

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return updatedDoctor;
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable(value = "id") Integer doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", doctorId));

        doctorRepository.delete(doctor);

        return ResponseEntity.ok().build();
    }
}
