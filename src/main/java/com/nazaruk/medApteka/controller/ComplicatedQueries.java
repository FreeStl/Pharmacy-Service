package com.nazaruk.medApteka.controller;

import com.nazaruk.medApteka.exeption.ResourceNotFoundException;
import com.nazaruk.medApteka.model.Component;
import com.nazaruk.medApteka.model.Medicine;
import com.nazaruk.medApteka.model.Patient;
import com.nazaruk.medApteka.model.entityEnums.MedClass;
import com.nazaruk.medApteka.repository.ComponentRepository;
import com.nazaruk.medApteka.repository.MedicineRepository;
import com.nazaruk.medApteka.repository.OrdersRepository;
import com.nazaruk.medApteka.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ComplicatedQueries {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ComponentRepository componentRepository;

    @GetMapping("/patientForgotOrder")
    public List<Object[]> patientForgotOrder() {
        return patientRepository.patientForgotOrder1();
    }

    @GetMapping("/patientWaiting")
    public List<Object[]> patientWaiting() {
        return patientRepository.patientWaiting2();
    }

    @GetMapping("/patientWaitingByClass")
    public List<Object[]> patientWaiting(@RequestParam MedClass medClass) {
        return patientRepository.patientWaiting2(medClass);
    }

    @GetMapping("/popularMedsTop")
    public List<Medicine> popularMedsTop() {
        return medicineRepository.popularMedsTop3();
    }

    @GetMapping("/popularMedsTopByCategory")
    public List<Medicine> popularMedsTop(@RequestParam MedClass medClass) {
        return medicineRepository.popularMedsTop3(medClass);
    }

    @GetMapping("/MedsUsedForPeriod")
    public Integer MedsUsedForPeriod(@RequestParam String name,
                                     @RequestParam Date from, Date till) {
        return medicineRepository.MedsUsedForPeriod4(name, from, till);
    }

    @GetMapping("/UserOrderList")
    public  List<Patient> UserOrderList(@RequestParam String name,
                                        @RequestParam Date from, Date till){
        return patientRepository.PatientOrderList5(name, from, till);
    }

    @GetMapping("/OutOfMeds")
    public List<Medicine> OutOfMeds(@RequestParam Integer criticalAmount){
        return medicineRepository.OutOfMeds67(criticalAmount);
    }

    @GetMapping("/OutOfMedsByCategory")
    public List<Medicine> OutOfMeds(@RequestParam MedClass medClass,
                                    @RequestParam Integer criticalAmount){
        return medicineRepository.OutOfMeds67(medClass, criticalAmount);
    }

    @GetMapping("OrdersInProd")
    public  List<Object[]> OrdersInProd(){
        return ordersRepository.ordersInProd8();
    }

    @GetMapping("MedsComponents")
    public  List<Component> MedsComponents(){
        return componentRepository.MedsComponents9();
    }

    @GetMapping("MedicineComponentFullInfo")
    public List<Object[]> MedicineComponentFullInfo(@RequestParam String medName){
        return medicineRepository.medicineComponentFullInfo11_13(medName);
    }

}
