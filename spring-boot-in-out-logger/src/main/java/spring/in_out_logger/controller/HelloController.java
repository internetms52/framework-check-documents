package spring.in_out_logger.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable(name = "name") String name) {
        return "Hello, " + name + "!";
    }

    @PostMapping("/hello")
    public String sayHelloPost(@RequestBody String name) {
        return "Hello, " + name + "!";
    }
}
