package bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookstore/hello-world")
public class HelloWorld {
    @GetMapping
    public String getHelloWorld() {
        return "Hello, World!";
    }
}
