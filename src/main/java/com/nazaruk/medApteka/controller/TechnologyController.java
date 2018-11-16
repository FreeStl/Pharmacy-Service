package com.nazaruk.medApteka.controller;

import com.nazaruk.medApteka.exeption.ResourceNotFoundException;
import com.nazaruk.medApteka.model.Technology;
import com.nazaruk.medApteka.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TechnologyController {

    @Autowired
    TechnologyRepository technologyRepository;

    @GetMapping("/technologies")
    public List<Technology> getAllTechnologys() {
        return technologyRepository.findAll();
    }

    @PostMapping("/technologies")
    public Technology createTechnology(@Valid @RequestBody Technology technology) {
        return technologyRepository.save(technology);
    }

    @GetMapping("/technologies/{id}")
    public Technology getTechnologyById(@PathVariable(value = "id") Integer technologyId) {
        return technologyRepository.findById(technologyId)
                .orElseThrow(() -> new ResourceNotFoundException("Technology", "id", technologyId));
    }

    @PutMapping("/technologies/{id}")
    public Technology updateTechnology(@PathVariable(value = "id") Integer technologyId,
                                 @Valid @RequestBody Technology technologyDetails) {
        Technology technology = technologyRepository.findById(technologyId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", technologyId));

        technology.setName(technologyDetails.getName());
        technology.setInfo(technologyDetails.getInfo());
        technology.setMedicine(technologyDetails.getMedicine());
        technology.setDays((technologyDetails.getDays()));
        technology.setComponents(technology.getComponents());

        Technology updatedTechnology = technologyRepository.save(technology);
        return updatedTechnology;
    }

    @DeleteMapping("/technologies/{id}")
    public ResponseEntity<?> deleteTechnology(@PathVariable(value = "id") Integer technologyId) {
        Technology technology = technologyRepository.findById(technologyId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", technologyId));

        technologyRepository.delete(technology);

        return ResponseEntity.ok().build();
    }
}
