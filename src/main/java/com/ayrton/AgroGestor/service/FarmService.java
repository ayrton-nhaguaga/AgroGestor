package com.ayrton.AgroGestor.service;

import com.ayrton.AgroGestor.dto.FarmDTO;
import com.ayrton.AgroGestor.model.Farm;
import com.ayrton.AgroGestor.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmService {

    @Autowired
    private FarmRepository farmRepository;

    public Farm createFarm(FarmDTO dto){
        Farm farm = new Farm();
        farm.setName(dto.getName());
        farm.setLocation(dto.getLocation());
        farm.setSizeHectares(dto.getSizeHectares());
        farm.setCreatedAt(dto.getCreatedAt());
        return farmRepository.save(farm);
    }

    public List<Farm> getAllFarms(){
        return farmRepository.findAll();
    }

    public Optional<Farm> getById(String id){
        return farmRepository.findById(id);
    }

    public List<Farm> getByName(String name){
        return farmRepository.findByNameIgnoreCase(name);
    }

    public List<Farm> getByLocation(String location){
        return farmRepository.findByLocationIgnoreCase(location);
    }

    public List<Farm> getBySizeHectares(double sizeHectares){
        return farmRepository.findBySizeHectares(sizeHectares);
    }

    public List<Farm> getByOwnerId(String ownerId){
        return farmRepository.findByOwnerId(ownerId);
    }

    public Optional<Farm> updateFarm(String id, FarmDTO dto){
        return farmRepository.findById(id)
                .map(farm -> {
                    farm.setName(dto.getName());
                    farm.setLocation(dto.getLocation());
                    farm.setSizeHectares(dto.getSizeHectares());
                    farm.setOwnerId(dto.getOwnerId());
                    return farmRepository.save(farm);
                });
    }

    public boolean deleteFarm(String id){
        return farmRepository.findById(id)
                .map(farm -> {
                    farmRepository.delete(farm);
                    return true;
                }).orElse(false);
    }
}
