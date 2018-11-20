package com.nazaruk.medApteka.controller;

import com.nazaruk.medApteka.exeption.ResourceNotFoundException;
import com.nazaruk.medApteka.model.Component;
import com.nazaruk.medApteka.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ComponentController {

    @Autowired
    ComponentRepository componentRepository;

    @GetMapping("/components")
    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    @PostMapping("/components")
    public Component createComponent(@Valid @RequestBody Component component) {
        return componentRepository.save(component);
    }

    @GetMapping("/components/{id}")
    public Component getComponentById(@PathVariable(value = "id") Integer componentId) {
        return componentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component", "id", componentId));
    }

    @PutMapping("/components/{id}")
    public Component updateComponent(@PathVariable(value = "id") Integer componentId,
                                   @Valid @RequestBody Component componentDetails) {
        Component component = componentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", componentId));

        component.setName(componentDetails.getName());
        component.setPrice((componentDetails.getPrice()));
        component.setAmout(componentDetails.getAmout());

        Component updatedComponent = componentRepository.save(component);
        return updatedComponent;
    }

    @DeleteMapping("/components/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable(value = "id") Integer componentId) {
        Component component = componentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", componentId));

        componentRepository.delete(component);

        return ResponseEntity.ok().build();
    }
}
