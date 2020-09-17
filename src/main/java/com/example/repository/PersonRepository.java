package com.example.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import ru.home.somewebservice.common.Address;
import ru.home.somewebservice.common.Person;
import ru.home.somewebservice.common.Town;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PersonRepository {
    private static final Map<String, Person> PERSONS = new HashMap<>();

    @PostConstruct
    public void initData() {
        createPerson("Vasya", "Pupkin", "Pupkovich", 25, 5, 4, "Super", Town.MOSCOW);
        createPerson("Kat'ka", "Dronova", "Jgbanik", 23, 2, 5, "Avgan", Town.KRASNOGORSK);
    }

    private void createPerson(String firstName,
                              String surname,
                              String middleName,
                              int age,
                              int build,
                              int room,
                              String street,
                              Town town) {
        Address address = new Address();
        address.setBuild(BigInteger.valueOf(build));
        address.setRoom(BigInteger.valueOf(room));
        address.setStreet(street);
        address.setTown(town);

        Person person = new Person();
        person.setFirstName(firstName);
        person.setSurname(surname);
        person.setMiddleName(middleName);
        person.setAge(age);
        person.setAddress(address);

        PERSONS.put(person.getFirstName(), person);
    }

    public Person findPerson(String name) {
        Assert.notNull(name, "The country's name must not be null");
        return PERSONS.get(name);
    }
}
