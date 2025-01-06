package bookstore.controller;

import bookstore.service.LoadDatasetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookstore/admin/load-dataset")
public class LoadDatasetController {
    @GetMapping
    public boolean LoadDataSet() {
        boolean result = LoadDatasetService.execute();
        return result;
    }
}
