package com.nazaruk.medApteka.controller;

import com.nazaruk.medApteka.exeption.ResourceNotFoundException;
import com.nazaruk.medApteka.model.Receipt;
import com.nazaruk.medApteka.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReceiptController {

    @Autowired
    ReceiptRepository receiptRepository;

    @GetMapping("/receipts")
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    @PostMapping("/receipts")
    public Receipt createReceipt(@Valid @RequestBody Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @GetMapping("/receipts/{id}")
    public Receipt getReceiptById(@PathVariable(value = "id") Integer receiptId) {
        return receiptRepository.findById(receiptId)
                .orElseThrow(() -> new ResourceNotFoundException("Receipt", "id", receiptId));
    }

    @PutMapping("/receipts/{id}")
    public Receipt updateReceipt(@PathVariable(value = "id") Integer receiptId,
                                 @Valid @RequestBody Receipt receiptDetails) {
        Receipt receipt = receiptRepository.findById(receiptId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", receiptId));

        receipt.setDiagnos(receiptDetails.getDiagnos());
        receipt.setInstruction(receiptDetails.getInstruction());
        receipt.setAmout(receiptDetails.getAmout());

        Receipt updatedReceipt = receiptRepository.save(receipt);
        return updatedReceipt;
    }

    @DeleteMapping("/receipts/{id}")
    public ResponseEntity<?> deleteController(@PathVariable(value = "id") Integer receiptId) {
        Receipt receipt = receiptRepository.findById(receiptId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", receiptId));

        receiptRepository.delete(receipt);

        return ResponseEntity.ok().build();
    }
}