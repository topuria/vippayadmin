package ge.vippay.admin.service;

import ge.vippay.admin.entity.Language;
import ge.vippay.admin.model.LanguageDTO;
import ge.vippay.admin.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public void createLanguage(LanguageDTO language) {
        Language language1 = new Language();
        language1.setCode(language.getLanguageCode());
        languageRepository.save(language1);
    }

    public List<Language> getLanguages(){
        return languageRepository.findAll();
    }

    public void deleteLanguage(int id) {
        languageRepository.deleteById(id);
    }
}
