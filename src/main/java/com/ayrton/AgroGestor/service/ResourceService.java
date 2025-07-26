package com.ayrton.AgroGestor.service;

import com.ayrton.AgroGestor.dto.ResourceDTO;
import com.ayrton.AgroGestor.model.Resource;
import com.ayrton.AgroGestor.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public Resource createResource(ResourceDTO dto){
        Resource resource = new Resource();
        resource.setName(dto.getName());
        resource.setType(dto.getType());
        resource.setActive(dto.isActive());
        return resourceRepository.save(resource);
    }

    public List<Resource> getAllResources(){
        return resourceRepository.findAll();
    }

    public Optional<Resource> getById(String id){
        return resourceRepository.findById(id);
    }

    public List<Resource> getByNameIgnoreCase(String name){
        return resourceRepository.findByNameIgnoreCase(name);
    }

    public List<Resource> getByTypeIgnoreCase(String type){
        return resourceRepository.findByTypeIgnoreCase(type);
    }

    public List<Resource> getByActive(boolean active){
        return resourceRepository.findByActive(active);
    }

    // metodo para marcar uso de um recurso ex tractor

    public Optional<Resource> updateResource(String id, ResourceDTO dto){
        return resourceRepository.findById(id)
                .map(resource -> {
                    resource.setName(dto.getName());
                    resource.setType(dto.getType());
                    resource.setActive(dto.isActive());
                    return resourceRepository.save(resource);
                });
    }

    public boolean deleteResource(String id){
        return resourceRepository.findById(id)
                .map(resource -> {
                    resourceRepository.delete(resource);
                    return true;
                })
                .orElse(false);
    }
}
