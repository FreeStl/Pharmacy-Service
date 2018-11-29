package com.nazaruk.medApteka.controller;

import com.nazaruk.medApteka.exeption.ResourceNotFoundException;
import com.nazaruk.medApteka.model.Elements;
import com.nazaruk.medApteka.model.Medicine;
import com.nazaruk.medApteka.model.Patient;
import com.nazaruk.medApteka.model.entityEnums.MedClass;
import com.nazaruk.medApteka.repository.ElementsRepository;
import com.nazaruk.medApteka.repository.MedicineRepository;
import com.nazaruk.medApteka.repository.OrdersRepository;
import com.nazaruk.medApteka.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

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
    ElementsRepository elementsRepository;

    @GetMapping("/patientForgotOrder")
    public List<Object[]> patientForgotOrder() {
        return patientRepository.patientForgotOrder1();
    }

    @GetMapping("/patientWaiting")
    public List<Object[]> patientWaiting(
            @RequestParam(value = "class", required = false) MedClass medClass
    ) {
        if (medClass == null){
            return patientRepository.patientWaiting2();
        } else {
            return patientRepository.patientWaiting2(medClass);
        }
    }

    @GetMapping("/popularMedsTop")
    public List<Medicine> popularMedsTop(
            @RequestParam(value = "class", required = false) MedClass medClass
    ) {
        if (medClass == null){
            return medicineRepository.popularMedsTop3();
        } else {
            return medicineRepository.popularMedsTop3(medClass.name());
        }
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
    public  List<Elements> MedsComponents(){
        return elementsRepository.MedsComponents9();
    }

    @GetMapping("MedicineComponentFullInfo")
    public List<Object[]> MedicineComponentFullInfo(@RequestParam String medName){
        return medicineRepository.medicineComponentFullInfo11_13(medName);
    }

}
