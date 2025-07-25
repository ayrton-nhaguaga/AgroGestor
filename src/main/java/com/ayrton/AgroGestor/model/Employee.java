package com.ayrton.AgroGestor.model;

import com.ayrton.AgroGestor.enums.EmployeeSpeciality;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "employees")
@Data
public class Employee {

    @Id
    private String id;
    private String name;

    @Email
    private String email;

    private String phone;
    private double rating;
    private List<EmployeeSchedule> schedules = new ArrayList<>();
    private List<EmployeeSpeciality> speciality = new ArrayList<>();
}
