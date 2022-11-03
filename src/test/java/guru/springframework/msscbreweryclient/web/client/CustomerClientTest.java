package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class CustomerClientTest {

    @Autowired
    CustomerClient customerClient;

    @Test
    void getCustomerById() {
        CustomerDto customerDto = customerClient.getCustomerById(UUID.randomUUID());

        assertNotNull(customerDto);
    }

    @Test
    void saveNewCustomer() {
        CustomerDto customerDto = CustomerDto.builder().name("New Customer").build();
        URI uri = customerClient.saveNewCustomer(customerDto);
        assertNotNull(uri);
        log.info("URI: "+uri);
    }

    @Test
    void updateCustomer() {
        CustomerDto customerDto = CustomerDto.builder().name("Existing Customer").build();
        customerClient.updateCustomer(UUID.randomUUID(),customerDto);
    }

    @Test
    void deleteCustomer() {
        customerClient.deleteCustomer(UUID.randomUUID());
    }
}