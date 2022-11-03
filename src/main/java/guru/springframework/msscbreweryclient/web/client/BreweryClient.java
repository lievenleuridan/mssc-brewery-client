package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
public class BreweryClient {

    final
    BreweryProperties breweryProperties;
    private final String apiHost;
    public final String BEER_PATH_V1 = "/api/v1/beer/";

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder, BreweryProperties breweryProperties) {
        this.restTemplate = restTemplateBuilder.build();
        this.breweryProperties = breweryProperties;
        this.apiHost = breweryProperties.getApiHost();
    }

    public BeerDto getBeerById(UUID beerId) {
        return restTemplate.getForObject(apiHost+BEER_PATH_V1 + beerId, BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(apiHost+BEER_PATH_V1, beerDto);
    }

    public void updateBeer(UUID beerId, BeerDto beerDto) {
        restTemplate.put(apiHost+BEER_PATH_V1+beerId, beerDto);
    }

    public void deleteBeer(UUID beerId) {
        restTemplate.delete(apiHost+BEER_PATH_V1+beerId);
    }
}
