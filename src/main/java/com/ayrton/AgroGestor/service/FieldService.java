package com.ayrton.AgroGestor.service;

import com.ayrton.AgroGestor.dto.FieldDTO;
import com.ayrton.AgroGestor.model.Field;
import com.ayrton.AgroGestor.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    public Field createField(FieldDTO dto){
        Field field = new Field();
        field.setName(dto.getName());
        field.setFarmId(dto.getFarmId());
        field.setArea(dto.getArea());
        field.setCoordinates(dto.getCoordinates());
        return fieldRepository.save(field);
    }

    public List<Field> getAllFields(){
        return fieldRepository.findAll();
    }

    public Optional<Field> getById(String id){
        return fieldRepository.findById(id);
    }

    public List<Field> getByName(String name){
        return fieldRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Field> getByFarmId(String farmId){
        return fieldRepository.findByFarmId(farmId);
    }

    public List<Field> getByArea(double area){
        return fieldRepository.findByArea(area);
    }

    public Optional<Field> updateField(String id, FieldDTO dto){
        return fieldRepository.findById(id)
                .map(field -> {
                    field.setName(dto.getName());
                    field.setArea(dto.getArea());
                    field.setCoordinates(dto.getCoordinates());
                    return fieldRepository.save(field);
                });
    }

    public boolean deleteField(String id){
        return fieldRepository.findById(id)
                .map(field -> {
                    fieldRepository.delete(field);
                    return true;
                })
                .orElse(false);
    }
}
