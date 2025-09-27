package com.dev001.identify.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/manager")
public class ManagerController {


    @GetMapping
    public String get() {
        return "GET:: manager controller";
    }
    @PostMapping
    public String post() {
        return "POST:: manager controller";
    }
    @PutMapping
    public String put() {
        return "PUT:: manager controller";
    }
    @DeleteMapping
    public String delete() {
        return "DELETE:: manager controller";
    }

}
