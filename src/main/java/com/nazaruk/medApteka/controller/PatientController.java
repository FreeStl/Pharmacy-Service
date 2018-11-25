package com.nazaruk.medApteka.controller;


import com.nazaruk.medApteka.exeption.ResourceNotFoundException;
import com.nazaruk.medApteka.model.Patient;
import com.nazaruk.medApteka.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;

@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @PostMapping("/patients")
    public Patient createPatient(@Valid @RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable(value = "id") Integer patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));
    }

    @GetMapping("/patients/number/")
    public Patient getPatientByNumber(@RequestParam(value = "number") String number){
        return patientRepository.findByNumber(number)
                .orElseThrow(() -> new ResourceNotFoundException("getPatientByNumber"));
    }

    @PutMapping("/patients/{id}")
    public Patient updatePatient(@PathVariable(value = "id") Integer patientId,
                               @Valid @RequestBody Patient patientDetails) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", patientId));

        patient.setName(patientDetails.getName());
        patient.setSurname(patientDetails.getSurname());
        patient.setMidname(patientDetails.getMidname());
        patient.setNumber((patientDetails.getNumber()));
        patient.setAdress(patientDetails.getAdress());
        patient.setAge(patientDetails.getAge());

        Patient updatedPatient = patientRepository.save(patient);
        return updatedPatient;
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable(value = "id") Integer patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", patientId));

        patientRepository.delete(patient);

        return ResponseEntity.ok().build();
    }
}
