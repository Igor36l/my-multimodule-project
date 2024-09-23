package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Sellers")
public class Sellers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seller_id;

    @Column(nullable = false, unique = true)
    private Long user_id;

    @Column(nullable = false)
    private String organization_name;

    private String organization_description;
    private String organization_address;
    private String organization_phone;

    @Column(nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime updated_at;

}
