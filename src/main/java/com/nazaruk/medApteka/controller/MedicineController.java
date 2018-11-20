package com.nazaruk.medApteka.controller;

import com.nazaruk.medApteka.exeption.ResourceNotFoundException;
import com.nazaruk.medApteka.model.Medicine;
import com.nazaruk.medApteka.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicineController {

    @Autowired
    MedicineRepository medicineRepository;

    @GetMapping("/medicines")
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @PostMapping("/medicines")
    public Medicine createMedicine(@Valid @RequestBody Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @GetMapping("/medicines/{id}")
    public Medicine getMedicineById(@PathVariable(value = "id") Integer medicineId) {
        return medicineRepository.findById(medicineId)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine", "id", medicineId));
    }

    @PutMapping("/medicines/{id}")
    public Medicine updateMedicine(@PathVariable(value = "id") Integer medicineId,
                                 @Valid @RequestBody Medicine medicineDetails) {
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", medicineId));

        medicine.setName(medicineDetails.getName());
        medicine.setMedClass(medicineDetails.getMedClass());
        medicine.setMedType(medicineDetails.getMedType());
        medicine.setPrice((medicineDetails.getPrice()));
        medicine.setAmout(medicineDetails.getAmout());
        medicine.setSoldCount(medicineDetails.getSoldCount());
        medicine.setComponents(medicineDetails.getComponents());

        Medicine updatedMedicine = medicineRepository.save(medicine);
        return updatedMedicine;
    }

    @DeleteMapping("/medicines/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable(value = "id") Integer medicineId) {
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", medicineId));

        medicineRepository.delete(medicine);

        return ResponseEntity.ok().build();
    }
}
