package net.agnina.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.agnina.billingservice.model.Customer;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Bill {
    @Id @GeneratedValue
    private long id;
    private long customerId;
    private Date billingDate;
    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItems;
    @Transient
    private Customer customer;

}
