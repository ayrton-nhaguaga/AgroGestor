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

    List<Employee> findByNameIgnoreCase(String name);

    List<Employee> findByEmailIgnoreCase(String email);

    List<Employee> findByRating(int rating);

    List<Employee> findBySpeciality(EmployeeSpeciality speciality);
}
