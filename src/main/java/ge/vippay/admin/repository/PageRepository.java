package ge.vippay.admin.repository;

import ge.vippay.admin.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {
    Optional<Page> findByName(String name);
}
