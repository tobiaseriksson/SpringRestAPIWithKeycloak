package com.tsoft.playground.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired(required=true)
    public Names names;

    /**
     * Endpoint /help that provides som helpful text
     * NOTE! that this endpoint is made available to all / no security, through the SecurityConfig class
     * @return
     */
    @GetMapping(value="/help")
    public String help() {
        return "Please use GET /api/names to get a list of the N first names, or a GET /api/names/idx to get a specific name";
    }


    /**
     * Returns a list of N names
     * This endpoint is secured
     * @return
     */
    @GetMapping(value = "/api/names")
    public List<String> list() {
        return names.all().stream().limit(15).collect(Collectors.toList());
    }

    /**
     * Returns a specific name from the list (based on an index)
     * This endpoint is secured
     * @param id
     * @return
     */
    @GetMapping(value = "/api/names/{id}")
    public String specificName(@PathVariable("id") Long id) {
        return names.byIndex(id);
    }
}
