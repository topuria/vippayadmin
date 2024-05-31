package ge.vippay.admin.controller;

import ge.vippay.admin.entity.Page;
import ge.vippay.admin.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/page")
public class PageController {
    private final PageService pageService;

    @Autowired
    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping
    public List<Page> getAllPages(){
        return pageService.getAllPages();
    }
}
