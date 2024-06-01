package ge.vippay.admin.repository;

import ge.vippay.admin.entity.Content;
import ge.vippay.admin.entity.Language;
import ge.vippay.admin.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    Optional<Content> findContentByPageAndLanguage(Page page, Language language);
}
