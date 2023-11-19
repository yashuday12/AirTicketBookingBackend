package com.hexaware.airticketbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.airticketbooking.entities.Admin;
/*
 * Author:Yashwanth
 * LastModifiedDate:15-11-2023
 * Description: It is Admin Repository which extends JPA Repsoitory to perform crud operation
 */
@Repository
public interface IAdminRepository extends JpaRepository<Admin,Integer>{
 public Admin findByAdminNameAndAdminPassword(String adminName, String adminPassword);
 Optional<Admin> findByAdminName(String adminName);
}
