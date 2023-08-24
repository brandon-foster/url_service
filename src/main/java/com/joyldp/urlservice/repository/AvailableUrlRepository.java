package com.joyldp.urlservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joyldp.urlservice.entity.AvailableUrlEntity;

@Repository
public interface AvailableUrlRepository extends CrudRepository<AvailableUrlEntity, String> {
    
}
