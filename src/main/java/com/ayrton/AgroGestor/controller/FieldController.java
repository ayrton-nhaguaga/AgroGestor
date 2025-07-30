package com.ayrton.AgroGestor.controller;

import com.ayrton.AgroGestor.dto.FieldDTO;
import com.ayrton.AgroGestor.model.Field;
import com.ayrton.AgroGestor.service.FieldService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping
    public ResponseEntity<Field> createField(@Valid @RequestBody FieldDTO dto){
        Field field = fieldService.createField(dto);
        return new ResponseEntity<>(field, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Field>> getAllFields(){
        List<Field> fields = fieldService.getAllFields();
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Field>> getById(@PathVariable String id){
        Optional<Field> field = fieldService.getById(id);
        return new ResponseEntity<>(field, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Field>> getByName(@Valid @RequestParam String name){
        List<Field> fields = fieldService.getByName(name);
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    @GetMapping("/farm_id/{farmId}")
    public ResponseEntity<List<Field>> getByFarmId(@PathVariable String farmId){
        List<Field> fields = fieldService.getByFarmId(farmId);
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    @GetMapping("/area")
    public ResponseEntity<List<Field>> getByArea(@Valid @RequestParam double area){
        List<Field> fields = fieldService.getByArea(area);
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Optional<Field>> updateField(@PathVariable String id, @Valid @RequestBody FieldDTO dto){
        Optional<Field> field = fieldService.updateField(id, dto);

        if (!field.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(field);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable String id){
        if (fieldService.deleteField(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
