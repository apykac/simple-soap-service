package com.example.endpoint;

import com.example.repository.CountryRepository;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.home.somewebservice.AddCountryRequest;
import ru.home.somewebservice.AddCountryResponse;
import ru.home.somewebservice.AddPersonRequest;
import ru.home.somewebservice.AddPersonResponse;
import ru.home.somewebservice.DeleteCountryRequest;
import ru.home.somewebservice.DeleteCountryResponse;
import ru.home.somewebservice.DeletePersonRequest;
import ru.home.somewebservice.DeletePersonResponse;
import ru.home.somewebservice.GetCountryRequest;
import ru.home.somewebservice.GetCountryResponse;
import ru.home.somewebservice.GetPersonRequest;
import ru.home.somewebservice.GetPersonResponse;

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
        response.setCountry(countryRepository.getCountry(request.getName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCountryRequest")
    @ResponsePayload
    public DeleteCountryResponse deleteCountry(@RequestPayload DeleteCountryRequest request) {
        DeleteCountryResponse response = new DeleteCountryResponse();
        response.setCountry(countryRepository.deleteCountry(request.getName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCountryRequest")
    @ResponsePayload
    public AddCountryResponse addCountry(@RequestPayload AddCountryRequest request) {
        AddCountryResponse response = new AddCountryResponse();
        response.setCountry(countryRepository.addCountry(request.getCountry()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonRequest")
    @ResponsePayload
    public GetPersonResponse getPerson(@RequestPayload GetPersonRequest request) {
        GetPersonResponse response = new GetPersonResponse();
        response.setPerson(personRepository.findPerson(request.getFirstName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
    @ResponsePayload
    public DeletePersonResponse deletePerson(@RequestPayload DeletePersonRequest request) {
        DeletePersonResponse response = new DeletePersonResponse();
        response.setPerson(personRepository.deletePerson(request.getFirstName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPersonRequest")
    @ResponsePayload
    public AddPersonResponse addPerson(@RequestPayload AddPersonRequest request) {
        AddPersonResponse response = new AddPersonResponse();
        response.setPerson(personRepository.addPerson(request.getPerson()));
        return response;
    }
}
