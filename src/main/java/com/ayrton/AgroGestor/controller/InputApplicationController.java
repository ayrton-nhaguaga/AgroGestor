package com.ayrton.AgroGestor.controller;

import com.ayrton.AgroGestor.dto.InputApplicationDTO;
import com.ayrton.AgroGestor.model.InputApplication;
import com.ayrton.AgroGestor.service.InputApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/input_applications")
public class InputApplicationController {

    @Autowired
    private InputApplicationService inputApplicationService;

    @PostMapping
    public ResponseEntity<InputApplication> createInputApplication(@Valid @RequestBody InputApplicationDTO dto){
        InputApplication inputApplication = inputApplicationService.createInputApplication(dto);
        return new ResponseEntity<>(inputApplication, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InputApplication>> getAllInputApplications(){
        List<InputApplication> inputApplications = inputApplicationService.getAllInputApplications();
        return new ResponseEntity<>(inputApplications, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<InputApplication>> getById(@PathVariable String id){
        Optional<InputApplication> inputApplication = inputApplicationService.getById(id);
        return new ResponseEntity<>(inputApplication, HttpStatus.OK);
    }

    @GetMapping("/crop_id/{cropId}")
    public ResponseEntity<List<InputApplication>> getByCropId(@PathVariable String cropId){
        List<InputApplication> inputApplications = inputApplicationService.getByCropId(cropId);
        return new ResponseEntity<>(inputApplications, HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<InputApplication>> getByProductIgnoreCase(@Valid @RequestParam String product){
        List<InputApplication> inputApplications = inputApplicationService.getByProductIgnoreCase(product);
        return new ResponseEntity<>(inputApplications, HttpStatus.OK);
    }

    @GetMapping("/application_date")
    public ResponseEntity<List<InputApplication>> getByApplicationDate(@RequestParam LocalDate applicationDate){
        List<InputApplication> inputApplications = inputApplicationService.getByApplicationDate(applicationDate);
        return new ResponseEntity<>(inputApplications, HttpStatus.OK);
    }

    @GetMapping("/employee")
    public ResponseEntity<List<InputApplication>> getByEmployeeIgnoreCase(@RequestParam String employee){
        List<InputApplication> inputApplications = inputApplicationService.getByEmployeeIgnoreCase(employee);
        return new ResponseEntity<>(inputApplications, HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Optional<InputApplication>> updateInputApplication(@PathVariable String id, @RequestBody InputApplicationDTO dto){
        Optional<InputApplication> inputApplication = inputApplicationService.updateInputApplication(id, dto);

        if (!inputApplication.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(inputApplication);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteInputApplication(@PathVariable String id){
        if (inputApplicationService.deleteInputApplication(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
