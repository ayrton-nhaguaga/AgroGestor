package com.ayrton.AgroGestor.service;

import com.ayrton.AgroGestor.dto.ResourceUsageDTO;
import com.ayrton.AgroGestor.model.ResourceUsage;
import com.ayrton.AgroGestor.repository.ResourceUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.Optional;

@Service
public class ResourceUsageService {

    @Autowired
    private ResourceUsageRepository resourceUsageRepository;


    public ResourceUsage registerUsage(ResourceUsageDTO dto) {
        // Verifica se há conflito de horários para o mesmo recurso
        boolean conflict = resourceUsageRepository.existsByResourceIdAndStartTimeBeforeAndEndTimeAfter(
                dto.getResourceId(), dto.getEndTime(), dto.getStartTime());

        if (conflict) {
            throw new RuntimeException("O recurso já está em uso nesse intervalo de tempo.");
        }

        // Cria e salva o uso do recurso
        ResourceUsage usage = new ResourceUsage();
        usage.setId(dto.getId());
        usage.setResourceId(dto.getResourceId());
        usage.setStartTime(dto.getStartTime());
        usage.setEndTime(dto.getEndTime());

        return resourceUsageRepository.save(usage);
    }

    public Optional<ResourceUsage> updateRegisterUsage(String id, ResourceUsageDTO dto) {
        return resourceUsageRepository.findById(id)
                .map(resourceUsage -> {
                    // Verifica se há sobreposição com outros usos do mesmo recurso
                    boolean conflict = resourceUsageRepository
                            .existsByResourceIdAndStartTimeBeforeAndEndTimeAfterAndIdNot(
                                    dto.getResourceId(), dto.getEndTime(), dto.getStartTime(), id);

                    if (conflict) {
                        throw new RuntimeException("Conflito de horário: o recurso já está em uso nesse intervalo.");
                    }


                    resourceUsage.setStartTime(dto.getStartTime());
                    resourceUsage.setEndTime(dto.getEndTime());

                    return resourceUsageRepository.save(resourceUsage);
                });
    }

}