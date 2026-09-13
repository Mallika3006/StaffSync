package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Role;
import com.mallika.EmployeeManagementSystem.repository.RoleRepository;
import org.springframework.data.domain.Sort;
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
        return roleRepository.save(role);
    }

    // GET ALL
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // GET BY ID
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found with id: " + id
                        ));
    }

    // UPDATE
    public Role updateRole(Integer id, Role roleDetails) {

        Role role = getRoleById(id);

        role.setRoleName(roleDetails.getRoleName());
        role.setDescription(roleDetails.getDescription());

        return roleRepository.save(role);
    }

    // DELETE
    public void deleteRole(Integer id) {

        Role role = getRoleById(id);

        roleRepository.delete(role);
    }

    // SEARCH BY NAME
    public List<Role> searchByRoleName(String roleName) {
        return roleRepository
                .findByRoleNameContainingIgnoreCase(roleName);
    }

    // EXACT ROLE NAME
    public Role getByExactRoleName(String roleName) {
        return roleRepository
                .findByRoleNameIgnoreCase(roleName)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found with name: " + roleName
                        ));
    }

    // SORT
    public List<Role> sortRoles(
            String field,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();

        return roleRepository.findAll(sort);
    }
}
