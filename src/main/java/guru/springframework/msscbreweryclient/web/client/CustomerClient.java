package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
public class CustomerClient {
    final
    BreweryProperties breweryProperties;
    public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    private final RestTemplate restTemplate;
    private final String apiHost;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder, BreweryProperties breweryProperties) {
        this.restTemplate = restTemplateBuilder.build();
        this.breweryProperties = breweryProperties;
        this.apiHost = breweryProperties.getApiHost();
    }



    public CustomerDto getCustomerById(UUID CustomerId) {
        return restTemplate.getForObject(apiHost+CUSTOMER_PATH_V1 + CustomerId, CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto CustomerDto) {
        return restTemplate.postForLocation(apiHost+CUSTOMER_PATH_V1, CustomerDto);
    }

    public void updateCustomer(UUID CustomerId, CustomerDto CustomerDto) {
        restTemplate.put(apiHost+CUSTOMER_PATH_V1+CustomerId, CustomerDto);
    }

    public void deleteCustomer(UUID CustomerId) {
        restTemplate.delete(apiHost+CUSTOMER_PATH_V1+CustomerId);
    }


}
