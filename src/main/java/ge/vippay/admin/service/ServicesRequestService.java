package ge.vippay.admin.service;

import ge.vippay.admin.entity.ServiceRequest;
import ge.vippay.admin.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServicesRequestService {
    private final ServiceRequestRepository serviceRequestRepository;

    @Autowired
    public ServicesRequestService(ServiceRequestRepository serviceRequestRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
    }

    public ServiceRequest save(ServiceRequest serviceRequest) {
        return serviceRequestRepository.save(serviceRequest);
    }

    public ServiceRequest update(long id, ServiceRequest serviceRequest) {
        ServiceRequest existing = findById(id);
        existing.setName(serviceRequest.getName());
        existing.setService(serviceRequest.getService());
        existing.setEmail(serviceRequest.getEmail());
        return serviceRequestRepository.save(existing);
    }

    public Page<ServiceRequest> searchServiceRequest(String query, Pageable pageable) {
        return serviceRequestRepository.searchServiceRequest(query, pageable);
    }

    public ServiceRequest findById(long id) {
        return serviceRequestRepository.getReferenceById(id);
    }

    public void delete(long id) {
        if (serviceRequestRepository.existsById(id)) {
            serviceRequestRepository.deleteById(id);
        }
    }
}
