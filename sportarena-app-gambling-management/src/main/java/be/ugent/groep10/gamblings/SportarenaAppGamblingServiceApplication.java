package be.ugent.groep10.gamblings;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import be.ugent.groep10.gamblings.domain.Bet;
import be.ugent.groep10.gamblings.domain.Wallet;
import be.ugent.groep10.gamblings.persistence.BetRepository;
import be.ugent.groep10.gamblings.persistence.WalletRepository;

@SpringBootApplication
public class SportarenaAppGamblingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppGamblingServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner populateBetDatabase(BetRepository betRepository) {
		return (args) -> {
			System.out.println("Populating BetDatabase");
			betRepository.save(new Bet(1, LocalDate.now().minus(1, ChronoUnit.DAYS), 1, 50.5));
			betRepository.save(new Bet(2, LocalDate.now().minus(1, ChronoUnit.DAYS), 1, 2));
			betRepository.save(new Bet(1, LocalDate.now().minus(1, ChronoUnit.DAYS), 2, 5));
			betRepository.save(new Bet(1, LocalDate.now().minus(1, ChronoUnit.DAYS), 3, 5.5));
			betRepository.save(new Bet(2, LocalDate.now().minus(1, ChronoUnit.DAYS), 2, 500.5));
			betRepository.save(new Bet(3, LocalDate.now().minus(1, ChronoUnit.DAYS), 1, 1.5));
		};
	}
	
	@Bean
	CommandLineRunner populateWalletDatabase(WalletRepository walletRepository) {
		return (args) -> {
			System.out.println("Populating WalletDatabase");
			walletRepository.save(new Wallet(1, 500));
			walletRepository.save(new Wallet(2, 50000));
			walletRepository.save(new Wallet(3, 10));
		};
	}
	
	@Bean
	CommandLineRunner testBetQueries(BetRepository betRepository) {
		return (args) -> {
			System.out.println("Printing all bets:");
			betRepository.findAll().forEach(System.out::println);
			
			System.out.println("Printing all bets from user 1:");
			betRepository.findBetsByPlacedByMember(1).forEach(System.out::println);
			
			System.out.println("Printing all bets from user 2:");
			betRepository.findBetsByPlacedByMember(2).forEach(System.out::println);
			
			System.out.println("Printing all bets from user 3:");
			betRepository.findBetsByPlacedByMember(3).forEach(System.out::println);
			
			System.out.println("Printing all bets for event 1:");
			betRepository.findBetsByScheduleItemId(1).forEach(System.out::println);
			
			System.out.println("Printing all bets for event 2:");
			betRepository.findBetsByScheduleItemId(2).forEach(System.out::println);
			
			System.out.println("Printing all bets for event 3:");
			betRepository.findBetsByScheduleItemId(3).forEach(System.out::println);
		};
	}
	
	@Bean
	CommandLineRunner testWalletQueries(WalletRepository walletRepository) {
		return (args) -> {
			System.out.println("Printing all wallets:");
			walletRepository.findAll().forEach(System.out::println);
			
			System.out.println("Printing wallet from member 1:");
			System.out.println(walletRepository.findById((long)1));
			
		};
	}

}
