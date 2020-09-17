package com.example.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import ru.home.somewebservice.common.Country;
import ru.home.somewebservice.common.Currency;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CountryRepository {
    private static final Map<String, Country> COUNTRIES = new HashMap<>();

    @PostConstruct
    public void initData() {
        createCountry("Spain", "Madrid", Currency.EUR, 46704314);
        createCountry("Poland", "Warsaw", Currency.PLN, 38186860);
        createCountry("United Kingdom", "London", Currency.GBP, 63705000);
    }

    private void createCountry(String name, String capital, Currency currency, int population) {
        Country country = new Country();
        country.setName(name);
        country.setCapital(capital);
        country.setCurrency(currency);
        country.setPopulation(population);
        COUNTRIES.put(country.getName(), country);
    }

    public Country findCountry(String name) {
        Assert.notNull(name, "The country's name must not be null");
        return COUNTRIES.get(name);
    }
}
