package com.joyldp.urlservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "available_url", schema = "urlservice")
@Entity
public class AvailableUrlEntity {
    @Id
    @Column(name = "hash")
    private String hash;

    @Column(name = "original_url")
    private String originalUrl;
}
