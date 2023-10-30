package org.Lab4;

import org.aspectj.bridge.MessageUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;




@SpringBootApplication
public class TestApplication {

    private MessageUtil log;

    public static void main(String[] args) {
        SpringApplication.run(Lab4Application.class, args);
    }


	@Bean
	public CommandLineRunner demo(AddressBookRepository abrepo, BuddyInfoRepository budrepo) {
		return (args) -> {
			// save a few buddies
			BuddyInfo b1 = new BuddyInfo("Jack F", "613-444-3323", "Ottawa");
			BuddyInfo b2 = new BuddyInfo("Donald T", "613-233-4323", "Gatinau");
			BuddyInfo b3 = new BuddyInfo("Michelle O", "613-645-9342", "Nepean");
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
			AddressBook ab2 = new AddressBook();
			abrepo.save(ab);
			abrepo.save(ab2);

			ab.addBuddy(b1);
			ab.addBuddy(b2);
			ab.addBuddy(b3);
			abrepo.save(ab);

			AddressBook result = abrepo.findById(ab.getId()).orElse(null);
			if (result != null){
				log.info("Buddies found in the Address Book");
				log.info("-------------------------------");
				log.info(result.toString());

			}

		};
	}

}
