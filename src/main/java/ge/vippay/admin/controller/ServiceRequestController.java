package ge.vippay.admin.controller;

import ge.vippay.admin.entity.ServiceRequest;
import ge.vippay.admin.model.ServiceRequestDTO;
import ge.vippay.admin.service.ServicesRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/request")
public class ServiceRequestController {
    private final ServicesRequestService servicesRequestService;

    @Autowired
    public ServiceRequestController(ServicesRequestService servicesRequestService) {
        this.servicesRequestService = servicesRequestService;
    }

    @GetMapping
    public Page<ServiceRequest> getServiceRequests(
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return servicesRequestService.searchServiceRequest(query, pageable);
    }

    @PostMapping("/create")
    public ResponseEntity<ServiceRequest> addRequest(@RequestBody ServiceRequestDTO message) {
        ServiceRequest newRequest = dtoToEntity(message);
        return ResponseEntity.ok(servicesRequestService.save(newRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ServiceRequest> updateRequest(@PathVariable long id, @RequestBody ServiceRequestDTO message) {
        ServiceRequest updatedMessage = dtoToEntity(message);
        return ResponseEntity.ok(servicesRequestService.update(id, updatedMessage));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable long id) {
        servicesRequestService.delete(id);
        return ResponseEntity.ok("Service Request With Id " + id + " deleted");
    }

    private ServiceRequest dtoToEntity(ServiceRequestDTO dto) {
        ServiceRequest request = new ServiceRequest();
        request.setName(dto.getName());
        request.setEmail(dto.getEmail());
        request.setService(dto.getService());
        return request;
    }
}
