package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private final String BEER_PATH_V1 = "/api/v1/beer/";
    private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    private String apihost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public BeerDto getBeerById(UUID beerId) {

        String apiUrl = apihost + BEER_PATH_V1 + beerId.toString();

        Class<BeerDto> expectedReturnObjTypeClass = BeerDto.class;

        BeerDto returnedBeer = restTemplate.getForObject(apiUrl, expectedReturnObjTypeClass);

        return returnedBeer;

    }

    public URI saveNewBeer(BeerDto beerToSave) {

        String apiUrl = apihost + BEER_PATH_V1;

        URI locationForSavedBeer = restTemplate.postForLocation(apiUrl, beerToSave);

        return locationForSavedBeer;

    }

    public void updateBeer(UUID beerId, BeerDto beerToUpdate) {

        String apiUrl = apihost + BEER_PATH_V1 + beerId.toString();

        restTemplate.put(apiUrl, beerToUpdate);

    }

    public void deleteBeer(UUID beerId){

        String apiUrl = apihost + BEER_PATH_V1 + beerId.toString();

        restTemplate.delete(apiUrl);

    }


    public CustomerDto getCustomerById(UUID customerId){

        String apiUrl = apihost+CUSTOMER_PATH_V1+customerId.toString();

        Class<CustomerDto> expectedReturnObjTypeClass = CustomerDto.class;

        CustomerDto returnedCustomer = restTemplate.getForObject(apiUrl, expectedReturnObjTypeClass);

        return returnedCustomer;
    };

    public URI saveNewCustomer(CustomerDto customerToSave){

        String apiUrl = apihost+CUSTOMER_PATH_V1;

        URI locationForSavedCustomer = restTemplate.postForLocation(apiUrl, customerToSave);

        return locationForSavedCustomer;

    };

    public void updateCustomer(UUID customerId, CustomerDto customerToUpdate){

        String apiUrl = apihost+CUSTOMER_PATH_V1+customerId.toString();

        restTemplate.put(apiUrl,customerToUpdate);

    };

    public void deleteCustomer(UUID customerId){

        String apiUrl = apihost+CUSTOMER_PATH_V1+customerId.toString();

        restTemplate.delete(apiUrl);

    };

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
