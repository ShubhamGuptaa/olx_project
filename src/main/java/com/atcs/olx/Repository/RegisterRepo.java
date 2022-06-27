package com.atcs.olx.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atcs.olx.Entity.Register;
@Repository
public interface RegisterRepo extends JpaRepository<Register,Long>{
    
}
