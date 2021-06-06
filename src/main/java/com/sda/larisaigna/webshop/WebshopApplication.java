package com.sda.larisaigna.webshop;

import com.sda.larisaigna.webshop.model.Account;
import com.sda.larisaigna.webshop.service.CustomerService;
import com.sda.larisaigna.webshop.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class WebshopApplication implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private MailService mailService;

	public static void main(String[] args) {

		SpringApplication.run(WebshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.refresh();

		// Put here your code

		Account account1 = new Account();
		account1.setId(1L);
		customerService.addCustomer(account1);

		customerService.getCustomerAccounts().forEach(account -> System.out.println(account));

		mailService.sendMail("larissa_im@yahoo.com", "client@example.com", "Mock mail subject", "Mock mail subject");
	}
}
