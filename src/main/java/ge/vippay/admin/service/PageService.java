package ge.vippay.admin.service;

import ge.vippay.admin.entity.Page;
import ge.vippay.admin.model.PageDTO;
import ge.vippay.admin.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {
    private final PageRepository pageRepository;

    @Autowired
    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public void createPage(PageDTO pageDTO) {
        Page newPage = new Page();
        newPage.setName(pageDTO.getPageName());
        pageRepository.save(newPage);
    }

    public List<Page> getAllPages() {
        return pageRepository.findAll();
    }

    public void deletePage(int id) {
        pageRepository.deleteById(id);
    }
}
