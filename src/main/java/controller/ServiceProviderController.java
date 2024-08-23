package controller;

import model.ServiceProvider;
import service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-providers")
public class ServiceProviderController {

    @Autowired
    private ServiceProviderService serviceProviderService;

    // Endpoint to get a list of all service providers
    @GetMapping
    public ResponseEntity<List<ServiceProvider>> getAllServiceProviders() {
        List<ServiceProvider> serviceProviders = serviceProviderService.getAllServiceProviders();
        return ResponseEntity.ok(serviceProviders);
    }

    // Endpoint to get service provider details by ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceProvider> getServiceProviderById(@PathVariable Long id) {
        ServiceProvider serviceProvider = serviceProviderService.getServiceProviderById(id);
        return ResponseEntity.ok(serviceProvider);
    }

    // Endpoint to add a new service provider
    @PostMapping("/create")
    public ResponseEntity<ServiceProvider> createServiceProvider(@RequestBody ServiceProvider serviceProvider) {
        ServiceProvider createdServiceProvider = serviceProviderService.createServiceProvider(serviceProvider);
        return ResponseEntity.ok(createdServiceProvider);
    }
}
