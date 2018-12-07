package com.zl.lqian.controller;


import com.zl.lqian.entity.Result;
import com.zl.lqian.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {

    @Autowired
    PersonService personService;

    @RequestMapping("/semaphore")
    public Result semaphore() {

        return personService.semaphore("");
    }

    @RequestMapping("/thread")
    public Result thread() {
        return personService.thread("");
    }
}
