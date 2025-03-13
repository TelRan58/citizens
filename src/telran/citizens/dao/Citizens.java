package telran.citizens.dao;

import telran.citizens.model.Person;

public interface Citizens {

    boolean add(Person person);

    boolean remove(int id);

    Person find(int id);

    // minAge - include, maxAge - include
    Iterable<Person> find(int minAge, int maxAge);

    Iterable<Person> find(String lastName);

    Iterable<Person> getAllPersonSortedById();

    Iterable<Person> getAllPersonSortedByLastName();

    Iterable<Person> getAllPersonSortedByAge();

    int size();
}
