package org.Lab4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Lab4Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab4Application.class, args);
	}

	/*private static final Logger log = LoggerFactory.getLogger(Sysc4806Lab4Application.class);

	@Bean
	public CommandLineRunner demo(AddressBookRepository abrepo, BuddyInfoRepository budrepo) {
		return (args) -> {
			// save a few buddies
			BuddyInfo b1 = new BuddyInfo("Jack F", "613-444-3323");
			BuddyInfo b2 = new BuddyInfo("Donald T", "613-233-4323");
			BuddyInfo b3 = new BuddyInfo("Michelle O", "613-645-9342");
			budrepo.save(b1);
			budrepo.save(b2);
			budrepo.save(b3);


			// fetch all buddies in the buddy table
			log.info("Buddies found with findAll():");
			log.info("-------------------------------");
			for (BuddyInfo b : budrepo.findAll()) {
				log.info(b.toString());
			}
			log.info("");

			//add to addressBook and fetch
			AddressBook ab = new AddressBook();
			abrepo.save(ab);

			ab.addBuddy(b1);
			ab.addBuddy(b2);
			ab.addBuddy(b3);
			abrepo.save(ab);

			AddressBook result = abrepo.findById(ab.getId()).orElse(null);
			if (result != null){
				log.info("Buddies found in the Address Book");
				log.info("-------------------------------");
				log.info(result.getBuddyInfoAsString());

			}

		};
	}*/

}
