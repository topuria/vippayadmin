package ge.vippay.admin.controller;

import ge.vippay.admin.entity.Language;
import ge.vippay.admin.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/language")
public class LanguageController {
    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public List<Language> getLanguages() {
        return languageService.getLanguages();
    }
}
