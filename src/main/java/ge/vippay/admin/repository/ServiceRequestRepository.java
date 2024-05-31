package ge.vippay.admin.repository;

import ge.vippay.admin.entity.ServiceRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    @Query("SELECT s FROM ServiceRequest s WHERE " +
            "LOWER(s.email) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(s.service) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<ServiceRequest> searchServiceRequest(String query, Pageable pageable);
}
