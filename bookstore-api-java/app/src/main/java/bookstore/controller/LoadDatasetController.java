package bookstore.controller;

import bookstore.service.LoadDatasetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookstore/admin/load-dataset")
public class LoadDatasetController {

    private final LoadDatasetService loadDatasetService;

    public LoadDatasetController(LoadDatasetService loadDatasetService) {
        this.loadDatasetService = loadDatasetService;
    }

    @GetMapping
    public boolean LoadDataSet() {
        boolean result = loadDatasetService.execute();
        return result;
    }
}
