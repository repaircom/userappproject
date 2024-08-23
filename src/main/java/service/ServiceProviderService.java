package service;

import model.ServiceProvider;
import repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProviderService {

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    // Method to get a list of all service providers
    public List<ServiceProvider> getAllServiceProviders() {
        return serviceProviderRepository.findAll();
    }

    // Method to get a service provider by ID
    public ServiceProvider getServiceProviderById(Long id) {
        Optional<ServiceProvider> serviceProviderOpt = serviceProviderRepository.findById(id);
        return serviceProviderOpt.orElseThrow(() -> new RuntimeException("Service Provider not found"));
    }

    // Method to create a new service provider
    public ServiceProvider createServiceProvider(ServiceProvider serviceProvider) {
        return serviceProviderRepository.save(serviceProvider);
    }
}
