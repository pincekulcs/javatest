package com.hg.medicalevolution.javatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class MedicalEvolutionSpringBootApplication {
    public static void main( String[] args )
    {
        SpringApplication.run(MedicalEvolutionSpringBootApplication.class, args);
    }
}
