package ge.vippay.admin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ge.vippay.admin.entity.Content;
import ge.vippay.admin.entity.Language;
import ge.vippay.admin.entity.Page;
import ge.vippay.admin.model.ContentDTO;
import ge.vippay.admin.model.UpdateContentDTO;
import ge.vippay.admin.repository.ContentRepository;
import ge.vippay.admin.repository.LanguageRepository;
import ge.vippay.admin.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final PageRepository pageRepository;
    private final LanguageRepository languageRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ContentService(ContentRepository contentRepository, PageRepository pageRepository, LanguageRepository languageRepository, ObjectMapper objectMapper) {
        this.contentRepository = contentRepository;
        this.pageRepository = pageRepository;
        this.languageRepository = languageRepository;
        this.objectMapper = objectMapper;
    }

    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    public ContentDTO getContentById(long id) {
        Content content = contentRepository.findById(id).orElseThrow(() -> new RuntimeException("Content not found"));
        return new ContentDTO(content.getPage().getName(), content.getLanguage().getCode(), objectMapper.convertValue(content.getContent(), String.class));
    }

    public void updateContentById(long id, ContentDTO contentDTO) throws JsonProcessingException {
        Content content = contentRepository.findById(id).orElseThrow(() -> new RuntimeException("Content not found"));
        content.setContent(objectMapper.writeValueAsString(contentDTO.getContentData()));
        contentRepository.save(content);
    }

    public void updateContentByPageAndLang(String pageName, String languageCode, UpdateContentDTO contentDTO) throws JsonProcessingException {
        Page page = pageRepository.findByName(pageName).orElseThrow(() -> new RuntimeException("Page not found"));
        Language language = languageRepository.findByCode(languageCode).orElseThrow(() -> new RuntimeException("Language not found"));
        Content content = contentRepository.findContentByPageAndLanguage(page, language).orElseThrow(() -> new RuntimeException("Content not found"));
        content.setContent(objectMapper.writeValueAsString(contentDTO.getContentData()));
        contentRepository.save(content);
    }

    public boolean createContent(ContentDTO contentDTO) throws JsonProcessingException {
        boolean created;
        Page page = pageRepository.findByName(contentDTO.getPageName()).orElseThrow(() -> new RuntimeException("Page not found"));
        Language language = languageRepository.findByCode(contentDTO.getLanguageCode()).orElseThrow(() -> new RuntimeException("Language not found"));
        Optional<Content> exists = contentRepository.findContentByPageAndLanguage(page, language);
        if (exists.isPresent()) {
            created = false;
        } else {
            Content content = new Content();
            content.setContent(objectMapper.writeValueAsString(contentDTO.getContentData()));
            content.setPage(page);
            content.setLanguage(language);
            contentRepository.save(content);
            created = true;
        }
        return created;
    }

    public ContentDTO getContentByPageNameAndLangCode(String pageName, String languageCode) {
        Page page = pageRepository.findByName(pageName).orElseThrow(() -> new RuntimeException("Page not found"));
        Language language = languageRepository.findByCode(languageCode).orElseThrow(() -> new RuntimeException("Language not found"));
        Content content = contentRepository.findContentByPageAndLanguage(page, language).orElseThrow(() -> new RuntimeException("Content not found"));
        ContentDTO contentDTO = new ContentDTO();
        contentDTO.setContentData(content.getContent());
        contentDTO.setPageName(pageName);
        contentDTO.setLanguageCode(languageCode);
        return contentDTO;

    }

}
