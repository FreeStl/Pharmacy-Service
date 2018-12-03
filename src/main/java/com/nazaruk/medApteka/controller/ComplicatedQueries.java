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

    MedClass medclass;

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
            return medicineRepository.popularMedsTop3(medClass.name());
    }

    @GetMapping("/medsUsedForPeriod")
    public Integer medsUsedForPeriod(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "from") Long from,
            @RequestParam(value = "to") Long till
    ) {
        Date fromDate = new Date(from);
        Date tillDate = new Date(till);
        return medicineRepository.medsUsedForPeriod4(name, fromDate, tillDate);
    }

    @GetMapping("/userOrderList")
    public  List<Patient> userOrderList(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "class", required = false) String medClass,
            @RequestParam(value = "from") Long from,
            @RequestParam(value = "to") Long till)
    {
        Date fromDate = new Date(from);
        Date tillDate = new Date(till);
        if (medClass.equals("null")){
            return patientRepository.patientOrderList5(name, null,
                    fromDate, tillDate);
        }
        return patientRepository.patientOrderList5(name, medClass,
                fromDate, tillDate);
    }

    @GetMapping("/outOfMeds")
    public List<Medicine> outOfMeds(
            @RequestParam(value = "class",required = false) String medClass,
            @RequestParam(value = "crit") Integer criticalAmount)
    {
        if (medClass.equals("null")){
            return medicineRepository.outOfMeds67(null, criticalAmount);
        }
        return medicineRepository.outOfMeds67(medClass, criticalAmount);
    }

    @GetMapping("/ordersInProd")
    public  List<Object[]> ordersInProd(){
        return ordersRepository.ordersInProd8();
    }

    @GetMapping("/medsComponentsInProd")
    public  List<Elements> medsComponents(){
        return elementsRepository.medsComponents9();
    }

    @GetMapping("/componentInfoByMedsName")
    public List<Elements> medicineComponentFullInfo(
            @RequestParam(value = "id") Integer id)
    {
        return elementsRepository.elementsByMedsName11_13(id);
    }
}
