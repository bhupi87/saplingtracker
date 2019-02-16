package com.salah.projectmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salah.projectmanager.domain.Client;


/**
 * Created by bhupendra.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
