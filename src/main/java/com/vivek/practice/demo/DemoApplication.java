package com.vivek.practice.demo;
import java.util.ArrayList;
import java.util.List;
import  java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vivek.practice.demo.model.Person;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		Person person1 = generateRandomPerson();
        Person person2 = generateRandomPerson();
        Person person3 = generateRandomPerson();

		List <Person> persons= new ArrayList<>();
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);

		Predicate <Person> ageFilter =(person)->{
			return person.getAge()>25;
		};

		Predicate <Person> cityFilterPredicate = (person)->{

			return person.getCity().equals("New York");
		};

		Predicate <Person> ageAndCityFPredicate =cityFilterPredicate.and(ageFilter);
		Predicate <Person> ageOrCityFPredicate =cityFilterPredicate.or(ageFilter);

		List <Person> filteredPerson = persons.stream().filter(ageAndCityFPredicate).collect(Collectors.toList());

		System.out.println("People who are older than 25 and live in New York:");
        filteredPerson.forEach(System.out::println);
        // Print details of generated persons
         
	}
	public static Person generateRandomPerson(){

		 Random random = new Random();
        String[] cities = {"New York", "London", "Paris", "Tokyo", "Berlin"};
        String[] names = {"Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace"};
        
        // Generate random values
        String randomName = names[random.nextInt(names.length)];
        String randomCity = cities[random.nextInt(cities.length)];
        int randomAge = random.nextInt(50) + 20; // Generate age between 20 and 69
        
        // Create and return a new Person object
        return new Person(randomName, randomCity, randomAge);

	}


}
