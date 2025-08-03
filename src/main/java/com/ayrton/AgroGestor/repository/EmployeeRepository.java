package com.ayrton.AgroGestor.repository;

import com.ayrton.AgroGestor.enums.EmployeeSpeciality;
import com.ayrton.AgroGestor.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    @Override
    Optional<Employee> findById(String id);

    @Override
    List<Employee> findAll();

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findByEmailContainingIgnoreCase(String email);


    List<Employee> findBySpeciality(EmployeeSpeciality speciality);
}
