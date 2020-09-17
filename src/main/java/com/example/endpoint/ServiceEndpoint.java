package com.example.endpoint;

import com.example.repository.CountryRepository;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.home.somewebservice.GetCountryRequest;
import ru.home.somewebservice.GetCountryResponse;
import ru.home.somewebservice.GetSomePersonDateRequest;
import ru.home.somewebservice.GetSomePersonDateResponse;

@Endpoint
public class ServiceEndpoint {
    private static final String NAMESPACE_URI = "http://home.ru/someWebService";

    private final CountryRepository countryRepository;
    private final PersonRepository personRepository;

    @Autowired
    public ServiceEndpoint(CountryRepository countryRepository, PersonRepository personRepository) {
        this.countryRepository = countryRepository;
        this.personRepository = personRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSomePersonDateRequest")
    @ResponsePayload
    public GetSomePersonDateResponse getCountry(@RequestPayload GetSomePersonDateRequest request) {
        GetSomePersonDateResponse response = new GetSomePersonDateResponse();
        response.setPerson(personRepository.findPerson(request.getFirstName()));
        return response;
    }
}
