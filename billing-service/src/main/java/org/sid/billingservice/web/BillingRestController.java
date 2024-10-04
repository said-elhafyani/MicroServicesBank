package org.sid.billingservice.web;


import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductRestClient;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class BillingRestController {

    private BillRepository billRepository;
    private CustomerRestClient customerRestClient;
    private ProductRestClient productRestClient;
    private ProductItemRepository productItemRepository;

    @Autowired
    public BillingRestController(BillRepository billRepository, CustomerRestClient customerRestClient, ProductRestClient productRestClient, ProductItemRepository productItemRepository) {
        this.billRepository = billRepository;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
        this.productItemRepository = productItemRepository;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id) {
        System.out.println("id = " + id);
        Bill bill = billRepository.findById(id).get();
        // on recupere customer a partire de customerRestClient de service customer
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
        // on recupere productItems a partire de productItemRestClient de service inventory
        bill.getProductItems().forEach(pi -> {
            pi.setProduct(productRestClient.getProductById(pi.getProductId()));
        });
        return bill;
    }
}
