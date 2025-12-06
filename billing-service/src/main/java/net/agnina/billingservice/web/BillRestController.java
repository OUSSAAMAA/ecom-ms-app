package net.agnina.billingservice.web;

import net.agnina.billingservice.entities.Bill;
import net.agnina.billingservice.feign.CustomerServiceRestClient;
import net.agnina.billingservice.feign.InventoryServiceRestClient;
import net.agnina.billingservice.model.Customer;
import net.agnina.billingservice.model.Product;
import net.agnina.billingservice.repository.BillRepository;
import net.agnina.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private CustomerServiceRestClient customerServiceRestClient;
    @Autowired
    private InventoryServiceRestClient inventoryServiceRestClient;

    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable long id) {
        Bill bill = billRepository.findById(id).orElse(null);
        if (bill == null) {
            throw new ResourceNotFoundException("Bill not found");
        }else  {
            Customer customer = customerServiceRestClient.findCustomerById(bill.getCustomerId());
            bill.setCustomer(customer);

            bill.getProductItems().forEach(productItem -> {
                productItem.setProduct(inventoryServiceRestClient.findProductById(productItem.getProductId()));
            });
        }
        return bill;
    }

}
