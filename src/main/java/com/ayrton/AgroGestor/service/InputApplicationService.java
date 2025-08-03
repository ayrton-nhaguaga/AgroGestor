package com.ayrton.AgroGestor.service;

import com.ayrton.AgroGestor.dto.InputApplicationDTO;
import com.ayrton.AgroGestor.model.InputApplication;
import com.ayrton.AgroGestor.repository.InputApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InputApplicationService {

    @Autowired
    private InputApplicationRepository inputApplicationRepository;

    public InputApplication createInputApplication(InputApplicationDTO dto){
        InputApplication inputApplication = new InputApplication();
        inputApplication.setCropId(dto.getCropId());
        inputApplication.setProduct(dto.getProduct());
        inputApplication.setDose(dto.getDose());
        inputApplication.setApplicationDate(dto.getApplicationDate());
        inputApplication.setEmployee(dto.getEmployee());
        inputApplication.setNotes(dto.getNotes());
        return inputApplicationRepository.save(inputApplication);
    }

    public List<InputApplication> getAllInputApplications(){
        return inputApplicationRepository.findAll();
    }

    public Optional<InputApplication> getById(String id){
        return inputApplicationRepository.findById(id);
    }

    public List<InputApplication> getByCropId(String cropId){
        return inputApplicationRepository.findByCropId(cropId);
    }

    public List<InputApplication> getByProductIgnoreCase(String product){
        return inputApplicationRepository.findByProductContainingIgnoreCase(product);
    }

    public List<InputApplication> getByApplicationDate(LocalDate applicationDate){
        return inputApplicationRepository.findByApplicationDate(applicationDate);
    }

    public List<InputApplication> getByEmployeeIgnoreCase(String employee){
        return inputApplicationRepository.findByEmployeeContainingIgnoreCase(employee);
    }

    public Optional<InputApplication> updateInputApplication(String id, InputApplicationDTO dto){
        return inputApplicationRepository.findById(id)
                .map(inputApplication -> {
                    inputApplication.setProduct(dto.getProduct());
                    inputApplication.setDose(dto.getDose());
                    inputApplication.setApplicationDate(dto.getApplicationDate());
                    inputApplication.setEmployee(dto.getEmployee());
                    inputApplication.setNotes(dto.getNotes());
                    return inputApplicationRepository.save(inputApplication);
                });
    }

    public boolean deleteInputApplication(String id){
        return inputApplicationRepository.findById(id)
                .map(inputApplication -> {
                    inputApplicationRepository.delete(inputApplication);
                    return true;
                })
                .orElse(false);
    }

}
