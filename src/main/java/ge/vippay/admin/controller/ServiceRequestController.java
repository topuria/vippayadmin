package ge.vippay.admin.controller;

import ge.vippay.admin.entity.ServiceRequest;
import ge.vippay.admin.model.ServiceRequestDTO;
import ge.vippay.admin.service.ServicesRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/request")
public class ServiceRequestController {
    private final ServicesRequestService servicesRequestService;

    @Autowired
    public ServiceRequestController(ServicesRequestService servicesRequestService) {
        this.servicesRequestService = servicesRequestService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<ServiceRequest>> getRequests() {
        return ResponseEntity.ok(servicesRequestService.findAll());
    }

    @PostMapping
    public ResponseEntity<ServiceRequest> addRequest(@RequestBody ServiceRequestDTO message) {
        ServiceRequest newRequest = dtoToEntity(message);
        return ResponseEntity.ok(servicesRequestService.save(newRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceRequest> updateRequest(@PathVariable long id, @RequestBody ServiceRequestDTO message) {
        ServiceRequest updatedMessage = dtoToEntity(message);
        return ResponseEntity.ok(servicesRequestService.update(id, updatedMessage));
    }

    @DeleteMapping("/{id}")
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
