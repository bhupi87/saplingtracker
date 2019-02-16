package com.salah.projectmanager.repo;

import com.salah.projectmanager.domain.Role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bhupendra.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
    
    List<Role> findAll();
}
