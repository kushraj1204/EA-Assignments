package app;

import domain.Person;
import domain.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.StopWatch;
import repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories("repository")
public class Application implements CommandLineRunner{

	@Autowired
	PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		addPeople();
		fetchPeople();
	}

	private void fetchPeople() {
		System.out.println("Started fetching people");
		StopWatch sw = new StopWatch();
		sw.start();
		List<Person> persons=personRepository.findAll();
		sw.stop();
		long totaltime=sw.getTotalTimeMillis();
		System.out.println("Fetched people with pets in"+totaltime+" ms");
	}

	public void addPeople(){
		System.out.println("Started adding people");
		StopWatch sw = new StopWatch();
		sw.start();
		List<Person> peopleList=new ArrayList<>();
		for (int x=0; x< 10000; x++){
			Person p=new Person("Kush"+x,"Raj");
			List<Pet> pets=new ArrayList<>();
			for (int j = 0; j < 10; j++) {
				Pet pet=new Pet("Cat",j+"");
				pets.add(pet);
			}
			p.setPets(pets);
			peopleList.add(p);
		}
		personRepository.saveAll(peopleList);
		sw.stop();
		long totaltime=sw.getTotalTimeMillis();
		System.out.println("Added people in"+totaltime+" ms");
	}

/*	Started adding people
	Added people in4643 ms
	Started fetching people
	Fetched people with pets in680 ms*/



}

