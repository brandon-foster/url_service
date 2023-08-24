package com.joyldp.urlservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joyldp.urlservice.entity.UrlEntity;

@Repository
public interface UrlRepository extends CrudRepository<UrlEntity, String> {
    
}
