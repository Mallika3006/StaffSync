package com.mallika.EmployeeManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequestDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phone;

    private LocalDate dateOfBirth;
    private LocalDate hireDate;

    private String address;

    @NotNull
    private Long departmentId;

    @NotNull
    private Long designationId;

    @NotNull
    private Long teamId;
}