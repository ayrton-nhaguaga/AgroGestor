package com.ayrton.AgroGestor.controller;


import com.ayrton.AgroGestor.dto.ResourceDTO;
import com.ayrton.AgroGestor.model.Resource;
import com.ayrton.AgroGestor.service.ResourceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping
    public ResponseEntity<Resource> createResource(@Valid @RequestBody ResourceDTO dto){
        Resource resource = resourceService.createResource(dto);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Resource>> getAllResources(){
        List<Resource> resources = resourceService.getAllResources();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Resource>> getById(@PathVariable String id){
        Optional<Resource> resource = resourceService.getById(id);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Resource>> getByNameIgnoreCase(@Valid @RequestParam String name){
        List<Resource> resources = resourceService.getByNameIgnoreCase(name);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/type")
    public ResponseEntity<List<Resource>> getByTypeIgnoreCase(@Valid @RequestParam String type){
        List<Resource> resources = resourceService.getByTypeIgnoreCase(type);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Resource>> getByActive(@RequestParam boolean active){
        List<Resource> resources = resourceService.getByActive(active);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Optional<Resource>> updateResource(@PathVariable String id, @Valid @RequestBody ResourceDTO dto){
        Optional<Resource> resource = resourceService.updateResource(id, dto);

        if (!resource.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable String id){
        if (resourceService.deleteResource(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
