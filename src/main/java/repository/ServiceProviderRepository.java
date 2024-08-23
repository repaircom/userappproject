package repository;

import model.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {
    // Find service providers by location
    List<ServiceProvider> findByLocation(String location);

    // Find service providers by service type (if needed)
    List<ServiceProvider> findByServiceType(String serviceType);

    // Add other custom queries if needed
}
