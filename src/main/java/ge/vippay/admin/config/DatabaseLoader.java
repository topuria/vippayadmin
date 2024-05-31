package ge.vippay.admin.config;

import ge.vippay.admin.entity.Language;
import ge.vippay.admin.entity.Page;
import ge.vippay.admin.repository.LanguageRepository;
import ge.vippay.admin.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final PageRepository pageRepository;
    private final LanguageRepository languageRepository;

    @Autowired
    public DatabaseLoader(PageRepository pageRepository, LanguageRepository languageRepository) {
        this.pageRepository = pageRepository;
        this.languageRepository = languageRepository;
    }

    @Override
    public void run(String... args) {
        //Page records
        Page about = new Page();
        about.setName("about");
        Page contact = new Page();
        contact.setName("contact");
        Page services = new Page();
        services.setName("services");
        Page serviceRequests = new Page();
        serviceRequests.setName("service_requests");
        Page messages = new Page();
        messages.setName("messages");
        Page feedbacks = new Page();
        feedbacks.setName("feedbacks");
        //Language records
        Language en = new Language();
        en.setCode("en");
        Language ge = new Language();
        ge.setCode("ge");
        Language ru = new Language();
        ru.setCode("ru");

        // Save Page Records to the database
        pageRepository.save(about);
        pageRepository.save(contact);
        pageRepository.save(services);
        pageRepository.save(serviceRequests);
        pageRepository.save(messages);
        pageRepository.save(feedbacks);

        //Save Language Records to the database
        languageRepository.save(en);
        languageRepository.save(ge);
        languageRepository.save(ru);
    }
}