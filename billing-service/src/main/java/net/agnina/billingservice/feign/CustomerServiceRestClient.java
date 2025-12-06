package net.agnina.billingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.agnina.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerServiceRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customer-service" , fallbackMethod = "defaultCustomer")
    Customer findCustomerById(@PathVariable long id);

    default Customer defaultCustomer(long id , Exception exc){
        exc.printStackTrace();
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName("cached customer");
        customer.setEmail("cached customer email");
        return customer;
    }
}
