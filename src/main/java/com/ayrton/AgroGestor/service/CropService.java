package com.ayrton.AgroGestor.service;

import com.ayrton.AgroGestor.dto.CropDTO;
import com.ayrton.AgroGestor.model.Crop;
import com.ayrton.AgroGestor.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CropService {

    @Autowired
    private CropRepository cropRepository;

    public Crop createCrop(CropDTO dto){
        Crop crop = new Crop();
        crop.setFieldId(dto.getFieldId());
        crop.setCulture(dto.getCulture());
        crop.setPlantedDate(dto.getPlantedDate());
        crop.setHarvestDate(dto.getHarvestDate());
        crop.setExpectedYield(dto.getExpectedYield());
        crop.setCostPerHectare(dto.getCostPerHectare());
        crop.setProductId(dto.getProductId());
        return cropRepository.save(crop);
    }

    public  Optional<Crop> getById(String id){
        return cropRepository.findById(id);
    }

    public List<Crop> getAllCrops(){
        return cropRepository.findAll();
    }

    public List<Crop> getByFieldId(String fieldId){
        return cropRepository.findByFieldId(fieldId);
    }

    public List<Crop> getByCultureIgnoreCase(String culture){
        return cropRepository.findByCultureContainingIgnoreCase(culture);
    }

    public List<Crop> getByPlantedDate(LocalDate plantedDate){
        return cropRepository.findByPlantedDate(plantedDate);
    }

    public Optional<Crop> updateCrop(String id, CropDTO dto){
        return cropRepository.findById(id)
                .map(crop -> {
                    crop.setFieldId(dto.getFieldId());
                    crop.setCulture(dto.getCulture());
                    crop.setPlantedDate(dto.getPlantedDate());
                    crop.setHarvestDate(dto.getHarvestDate());
                    crop.setExpectedYield(dto.getExpectedYield());
                    crop.setCostPerHectare(dto.getCostPerHectare());
                    crop.setProductId(dto.getProductId());
                    return cropRepository.save(crop);
                });
    }

    public boolean deleteCrop(String id){
        return cropRepository.findById(id)
                .map(crop -> {
                    cropRepository.delete(crop);
                    return true;
                })
                .orElse(false);
    }
}
