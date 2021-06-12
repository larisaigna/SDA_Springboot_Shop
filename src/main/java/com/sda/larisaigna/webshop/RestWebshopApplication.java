package com.sda.larisaigna.webshop;

import com.sda.larisaigna.webshop.model.Account;
import com.sda.larisaigna.webshop.model.Customer;
import com.sda.larisaigna.webshop.model.Product;
import com.sda.larisaigna.webshop.model.ProductCategory;
import com.sda.larisaigna.webshop.repository.AccountRepository;
import com.sda.larisaigna.webshop.service.CustomerService;
import com.sda.larisaigna.webshop.service.MailService;
import com.sda.larisaigna.webshop.service.OrderService;
import com.sda.larisaigna.webshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.sda.larisaigna.webshop.repository")
@EntityScan(basePackages = "com.sda.larisaigna.webshop.model")
public class RestWebshopApplication {


    public static void main(String[] args) {

        SpringApplication.run(RestWebshopApplication.class, args);
    }


}
