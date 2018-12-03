package com.nazaruk.medApteka.controller;

import com.nazaruk.medApteka.exeption.ResourceNotFoundException;
import com.nazaruk.medApteka.model.Elements;
import com.nazaruk.medApteka.repository.ElementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ElementsController {

    @Autowired
    ElementsRepository elementsRepository;

    @GetMapping("/elements")
    public List<Elements> getAllElementss() {
        return elementsRepository.findAll();
    }

    @PostMapping("/elements")
    public Elements createElements(@Valid @RequestBody Elements elements) {
        return elementsRepository.save(elements);
    }

    @GetMapping("/elements/{id}")
    public Elements getElementsById(@PathVariable(value = "id") Integer elementsId) {
        return elementsRepository.findById(elementsId)
                .orElseThrow(() -> new ResourceNotFoundException("Elements", "id", elementsId));
    }

    @PutMapping("/elements/{id}")
    public Elements updateElements(@PathVariable(value = "id") Integer elementsId,
                                     @Valid @RequestBody Elements elementsDetails) {
        Elements elements = elementsRepository.findById(elementsId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", elementsId));

        elements.setName(elementsDetails.getName());
        elements.setPrice((elementsDetails.getPrice()));
        elements.setAmount(elementsDetails.getAmount());

        Elements updatedElements = elementsRepository.save(elements);
        return updatedElements;
    }

    @DeleteMapping("/elements/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable(value = "id") Integer elementsId) {
        Elements elements = elementsRepository.findById(elementsId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", elementsId));

        elementsRepository.delete(elements);

        return ResponseEntity.ok().build();
    }
}
