package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        Arrays.stream(args).forEach(s -> System.out.print(s + ' '));
        System.out.println( "Hello World!");
        SpringApplication.run(App.class, args);
    }
}
