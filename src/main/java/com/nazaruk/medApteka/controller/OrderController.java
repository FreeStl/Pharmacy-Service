package com.nazaruk.medApteka.controller;


import com.nazaruk.medApteka.exeption.ConflictInputException;
import com.nazaruk.medApteka.exeption.ResourceNotFoundException;
import com.nazaruk.medApteka.model.Doctor;
import com.nazaruk.medApteka.model.Medicine;
import com.nazaruk.medApteka.model.Orders;
import com.nazaruk.medApteka.model.Patient;
import com.nazaruk.medApteka.repository.DoctorRepository;
import com.nazaruk.medApteka.repository.MedicineRepository;
import com.nazaruk.medApteka.repository.OrdersRepository;
import com.nazaruk.medApteka.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    MedicineRepository medicineRepository;

    @GetMapping("/orders")
    public Page<Orders> getAllOrders(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ordersRepository.findAll(pageable);
    }

    @PostMapping("/orders")
    public Orders createOrder(@Valid @RequestBody Orders order) {
        Doctor doctor = order.getDoctor();
        Patient patient = order.getPatient();

        Doctor existedDoctor = doctorRepository.checkIfExist(
                doctor.getName(), doctor.getSurname(), doctor.getMidname()
                ).orElse(new Doctor());

       if (!existedDoctor.equals(new Doctor())){
           order.setDoctor(existedDoctor);
       }


       Patient existedPatient = patientRepository.findByNumber(patient.getNumber())
               .orElse(new Patient());

       if(!existedPatient.equals(new Patient())){
           if (existedPatient.getName().equals(patient.getName()) &&
           existedPatient.getSurname().equals(patient.getSurname()) &&
           existedPatient.getMidname().equals(patient.getMidname())
           ){
               existedPatient.setAdress(patient.getAdress());
               existedPatient.setAge(patient.getAge());
               order.setPatient(existedPatient);
           } else {
               throw new ConflictInputException("Order", "Patient");
           }
       }

       order.setMedicine(medicineRepository.findById(order.getMedicine().getId())
               .orElse(order.getMedicine()));

       return ordersRepository.save(order);
    }

    @GetMapping("/orders/{id}")
    public Orders getOrderById(@PathVariable(value = "id") Integer orderId) {
        return ordersRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
    }

    @PutMapping("/orders/{id}")
    public Orders updateOrder(@PathVariable(value = "id") Integer orderId,
                                 @Valid @RequestBody Orders orderDetails) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", orderId));

        order.setDoctor(orderDetails.getDoctor());
        order.setPatient(orderDetails.getPatient());
        order.setReceipt(orderDetails.getReceipt());
        order.setMedicine((orderDetails.getMedicine()));
        order.setOrderStatus(orderDetails.getOrderStatus());

        Orders updatedOrder = ordersRepository.save(order);
        return updatedOrder;
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Integer orderId) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", orderId));

        ordersRepository.delete(order);

        return ResponseEntity.ok().build();
    }
}
