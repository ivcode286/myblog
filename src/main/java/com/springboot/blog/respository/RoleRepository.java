package com.springboot.blog.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleRepository,Long>   {
    Optional<RoleRepository> findByName(String name);
}
