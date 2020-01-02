package be.ugent.groep10.membermanagement;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import be.ugent.groep10.membermanagement.domain.Member;
import be.ugent.groep10.membermanagement.persistence.MemberRepository;

@SpringBootApplication
public class SportarenaAppMemberManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppMemberManagementApplication.class, args);
		System.out.println("Running...");
		
	}
	
	@Bean
	CommandLineRunner populateDatabase(MemberRepository memberRepository) {
		return (args) -> {
			memberRepository.deleteAll();
			
			memberRepository.save(new Member("eentje", "Jasper", "Maes", LocalDate.of(1996, Month.JULY, 24), LocalDate.now().minus(1, ChronoUnit.DAYS),"BE1"));
			memberRepository.save(new Member("tweetje", "Simeon", "Bruyland", LocalDate.of(1997, Month.MARCH, 26), LocalDate.now().minus(5, ChronoUnit.DAYS),"BE2"));
		};
	}
	
	@Bean
	CommandLineRunner testQueries(MemberRepository memberRepository) {
		return (args) -> {
			System.out.println("Printing all members:");
			memberRepository.findAll().forEach(System.out::println);
			
			System.out.println("Printing all members with last name 'Maes':");
			memberRepository.findMembersByLastName("Maes").forEach(System.out::println);
		};
	}

}
