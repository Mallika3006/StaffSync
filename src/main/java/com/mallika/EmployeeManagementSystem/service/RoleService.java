package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Role;
import com.mallika.EmployeeManagementSystem.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    // CREATE
    public Role createRole(Role role) {

        return roleRepository.createRole(role);
    }


    // GET ALL
    public List<Role> getAllRoles() {

        return roleRepository.getAllRoles();
    }


    // GET BY ID
    public Role getRoleById(Integer id) {

        return roleRepository.getRoleById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found with id: " + id
                        )
                );
    }


    // UPDATE
    public Role updateRole(
            Integer id,
            Role roleDetails) {

        // Check whether role exists
        getRoleById(id);

        return roleRepository.updateRole(
                id,
                roleDetails
        );
    }


    // DELETE
    public void deleteRole(Integer id) {

        // Check whether role exists
        getRoleById(id);

        roleRepository.deleteRole(id);
    }


    // SEARCH BY NAME
    public List<Role> searchByRoleName(
            String roleName) {

        return roleRepository.searchByRoleName(roleName);
    }


    // EXACT ROLE NAME
    public Role getByExactRoleName(
            String roleName) {

        return roleRepository
                .getByExactRoleName(roleName)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found with name: "
                                        + roleName
                        )
                );
    }


    // SORT
    public List<Role> sortRoles(
            String field,
            String direction) {

        return roleRepository.sortRoles(
                field,
                direction
        );
    }
}