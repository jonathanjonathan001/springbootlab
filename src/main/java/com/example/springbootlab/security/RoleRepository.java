package com.example.springbootlab.security;

import org.springframework.data.repository.ListCrudRepository;

public interface RoleRepository extends ListCrudRepository<Role,Long> {
}
