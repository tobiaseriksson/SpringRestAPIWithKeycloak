package com.tsoft.playground.spring;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Names {

    private List<String> names = new LinkedList<>();


    private static List<String> readFileFromResource(String filename) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("names_boys.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        return reader.lines().collect(Collectors.toList());
    }

    public Names() {
        names.addAll( readFileFromResource("names_boys.txt ") );
        names.addAll( readFileFromResource( "names_girls.txt") );
        System.out.println("Names initilized");
    }

    public List<String> all() {
        return names;
    }

    public String byIndex( Long idx ){
        return names.get(Math.toIntExact(idx));
    }


}
