package org.gentten.codegeneratorweb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CodeGeneratorWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorWebApplication.class, args);
    }

}
