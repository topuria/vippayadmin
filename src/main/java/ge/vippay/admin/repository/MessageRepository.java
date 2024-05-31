package ge.vippay.admin.repository;

import ge.vippay.admin.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE " +
            "LOWER(m.email) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(m.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(m.message) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Message> searchMessage(String query, Pageable pageable);
}
