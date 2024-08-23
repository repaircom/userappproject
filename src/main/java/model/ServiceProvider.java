package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "service_providers")
@Data
public class ServiceProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String serviceType;

    @Column(nullable = false)
    private String contactDetails;

    // Additional fields or methods if needed
}
