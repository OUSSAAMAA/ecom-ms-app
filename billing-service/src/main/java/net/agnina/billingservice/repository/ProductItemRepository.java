package net.agnina.billingservice.repository;

import net.agnina.billingservice.entities.Bill;
import net.agnina.billingservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
    List<ProductItem> findByBillId(long productId);
}
