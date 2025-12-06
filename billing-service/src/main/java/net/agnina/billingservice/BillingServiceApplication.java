package net.agnina.billingservice;

import net.agnina.billingservice.entities.Bill;
import net.agnina.billingservice.entities.ProductItem;
import net.agnina.billingservice.feign.CustomerServiceRestClient;
import net.agnina.billingservice.feign.InventoryServiceRestClient;
import net.agnina.billingservice.model.Customer;
import net.agnina.billingservice.model.Product;
import net.agnina.billingservice.repository.BillRepository;
import net.agnina.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(BillRepository billRepository,
                                        ProductItemRepository productItemRepository, List list){

        return args -> {
            List<Long> customersIds =List.of(1L,2L,3L);
            List<Long> productsIds =List.of(1L,2L,3L);

            customersIds.forEach(customersId -> {
               Bill bill = new Bill();
               bill.setBillingDate(new Date());
               bill.setCustomerId(customersId);
               billRepository.save(bill);
                productsIds.forEach(productId -> {
                   ProductItem productItem = new ProductItem();
                   productItem.setPrice(1000*Math.random()*500);
                   productItem.setQuantity(new Random().nextInt(10)+1);
                   productItem.setProductId(productId);
                   productItem.setBill(bill);
                   productItemRepository.save(productItem);
                });
            });
        };
    }
}
