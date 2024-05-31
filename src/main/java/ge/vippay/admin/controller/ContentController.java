package ge.vippay.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ge.vippay.admin.model.ContentDTO;
import ge.vippay.admin.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/content")
public class ContentController {
    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping("")
    public ResponseEntity<String> createContent(@RequestBody ContentDTO contentDTO) throws JsonProcessingException {
        contentService.createContent(contentDTO);
        return ResponseEntity.ok().body("Content created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateContent(@PathVariable long id, @RequestBody ContentDTO contentDTO) throws JsonProcessingException {
        contentService.updateContent(id, contentDTO);
        return ResponseEntity.ok().body("Content updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentDTO> getContent(@PathVariable long id) {
        return ResponseEntity.ok().body(contentService.getContentById(id));
    }
}
