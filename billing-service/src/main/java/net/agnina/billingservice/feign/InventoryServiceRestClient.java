package net.agnina.billingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.agnina.billingservice.model.Customer;
import net.agnina.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryServiceRestClient {
    @GetMapping("/products/{id}")
    @CircuitBreaker(name = "inventory-service" , fallbackMethod = "defaultProd")
    Product findProductById(@PathVariable long id);
    default Product defaultProd(long id) {
        Product product = new Product();
        product.setId(id);
        return  product;
    }
}
