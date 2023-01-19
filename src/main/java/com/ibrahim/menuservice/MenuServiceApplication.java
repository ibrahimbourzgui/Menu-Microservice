package com.ibrahim.menuservice;

import com.ibrahim.menuservice.entities.Menu;
import com.ibrahim.menuservice.repository.MenuRepository;
import org.apache.el.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class MenuServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MenuServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MenuRepository menuRepository, RepositoryRestConfiguration restConfiguration)
    {
        return args ->{
            restConfiguration.exposeIdsFor(Menu.class);
            menuRepository.findAll().forEach(m->{
                System.out.println(m);
            });
        };
    }

}
