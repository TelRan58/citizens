package telran.citizens.dao;

import telran.citizens.model.Person;

import java.time.LocalDate;
import java.util.*;

public class CitizensImpl implements Citizens {
    private static Comparator<Person> lastNameComparator;
    private static Comparator<Person> ageComparator;
    private TreeSet<Person> idCollection;
    private TreeSet<Person> lastNameCollection;
    private TreeSet<Person> ageCollection;

    static {
        lastNameComparator = (p1, p2) -> {
            int res = p1.getLastName().compareTo(p2.getLastName());
            return res != 0 ? res : Integer.compare(p1.getId(), p2.getId());
        };
        ageComparator = (p1, p2) -> {
            int res = Integer.compare(p1.getAge(), p2.getAge());
            return res != 0 ? res : Integer.compare(p1.getId(), p2.getId());
        };
    }

    public CitizensImpl() {
        idCollection = new TreeSet<>();
        ageCollection = new TreeSet<>(ageComparator);
        lastNameCollection = new TreeSet<>(lastNameComparator);
    }

    public CitizensImpl(List<Person> citizens) {
        this();
        citizens.forEach(p -> add(p));
    }

    // O(log(n))
    @Override
    public boolean add(Person person) {
        return person != null && idCollection.add(person) && lastNameCollection.add(person) && ageCollection.add(person);
    }

    // O(log(n))
    @Override
    public boolean remove(int id) {
        Person person = find(id);
        return person != null && idCollection.remove(person) && lastNameCollection.remove(person) && ageCollection.remove(person);
    }

    // O(log(n))
    @Override
    public Person find(int id) {
        Person pattern = new Person(id, null, null, null);
        Person person = idCollection.ceiling(pattern);
        return pattern.equals(person) ? person : null;
    }

    // O(log(n))
    @Override
    public Iterable<Person> find(int minAge, int maxAge) {
        LocalDate now = LocalDate.now();
        Person from = new Person(idCollection.first().getId() - 1, null, null, now.minusYears(minAge));
        Person to = new Person(idCollection.last().getId() + 1, null, null, now.minusYears(maxAge));
        return ageCollection.subSet(from, to);
    }

    // O(log(n))
    @Override
    public Iterable<Person> find(String lastName) {
        Person from = new Person(Integer.MIN_VALUE, null, lastName, null);
        Person to = new Person(Integer.MAX_VALUE, null, lastName, null);
        return lastNameCollection.subSet(from, to);
    }

    // O(1)
    @Override
    public Iterable<Person> getAllPersonSortedById() {
        return idCollection;
    }

    // O(1)
    @Override
    public Iterable<Person> getAllPersonSortedByLastName() {
        return lastNameCollection;
    }

    // O(1)
    @Override
    public Iterable<Person> getAllPersonSortedByAge() {
        return ageCollection;
    }

    // O(1)
    @Override
    public int size() {
        return idCollection.size();
    }
}
