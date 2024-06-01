package ge.vippay.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ge.vippay.admin.entity.Content;
import ge.vippay.admin.model.ContentDTO;
import ge.vippay.admin.model.UpdateContentDTO;
import ge.vippay.admin.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/content")
public class ContentController {
    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping()
    public ResponseEntity<String> createContent(@RequestBody ContentDTO contentDTO) throws JsonProcessingException {
        boolean isCreated = contentService.createContent(contentDTO);
        if (isCreated) {
            return ResponseEntity.ok().body("Content created");
        } else {
            return ResponseEntity.ok().body("Content on page " + contentDTO.getPageName() + " with lang " + contentDTO.getLanguageCode() + " Already Exists");
        }
    }

    @PutMapping("/page/{name}/lang/{code}")
    public ResponseEntity<String> updateContent(@PathVariable String name, @PathVariable String code, @RequestBody UpdateContentDTO contentDTO) throws JsonProcessingException {
        contentService.updateContentByPageAndLang(name, code, contentDTO);
        return ResponseEntity.ok().body("Content updated");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateContent(@PathVariable long id, @RequestBody ContentDTO contentDTO) throws JsonProcessingException {
        contentService.updateContentById(id, contentDTO);
        return ResponseEntity.ok().body("Content updated");
    }

    @GetMapping("/page/{name}/lang/{code}")
    public ResponseEntity<ContentDTO> getContentByPageAndLang(@PathVariable String name, @PathVariable String code) {

        return ResponseEntity.ok().body(contentService.getContentByPageNameAndLangCode(name, code));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentDTO> getContentById(@PathVariable long id) {
        return ResponseEntity.ok().body(contentService.getContentById(id));
    }

    @GetMapping("/all")
    public List<Content> getAllContent() {
        return contentService.getAllContents();
    }
}
