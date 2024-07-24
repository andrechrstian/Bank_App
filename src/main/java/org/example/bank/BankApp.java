package org.example.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApp {

    public static void main(String[] args) {
        System.out.println("Simple Bank Management App");
        SpringApplication.run(BankApp.class,args);
    }
}
