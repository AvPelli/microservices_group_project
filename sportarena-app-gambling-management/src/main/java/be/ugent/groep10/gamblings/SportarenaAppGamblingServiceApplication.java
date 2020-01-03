package be.ugent.groep10.gamblings;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import be.ugent.groep10.gamblings.adapters.messaging.Channels;
import be.ugent.groep10.gamblings.domain.Bet;
import be.ugent.groep10.gamblings.domain.BettableGame;
import be.ugent.groep10.gamblings.domain.Wallet;
import be.ugent.groep10.gamblings.persistence.BetRepository;
import be.ugent.groep10.gamblings.persistence.BettableGameRepository;
import be.ugent.groep10.gamblings.persistence.WalletRepository;

@SpringBootApplication
@EnableBinding(Channels.class)
public class SportarenaAppGamblingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppGamblingServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner populateBetDatabase(BettableGameRepository bettableGameRepository, BetRepository betRepository) {
		return (args) -> {
			System.out.println("Populating BetDatabase");
			bettableGameRepository.deleteAll();
			betRepository.deleteAll();
			
			BettableGame game1 = new BettableGame(1, LocalDate.now().plus(10, ChronoUnit.DAYS), 4);
			BettableGame game2 = new BettableGame(2, LocalDate.now().minus(10, ChronoUnit.DAYS), 1);
			BettableGame game3 = new BettableGame(3, LocalDate.now().minus(10, ChronoUnit.DAYS), 0.25);
			
			bettableGameRepository.save(game1);
			bettableGameRepository.save(game2);
			bettableGameRepository.save(game3);
			
//			bettableGameRepository.findById(game1.getId()).map(bettableGame -> {
//				return betRepository.save(new Bet(1, bettableGame, LocalDate.now(), 50, 1));
//			});
			
			betRepository.save(new Bet("1", game1, LocalDate.now(), 50, 1));
			betRepository.save(new Bet("2", game1, LocalDate.now(), 500, 1));
			betRepository.save(new Bet("3", game1, LocalDate.now(), 503, 2));
			betRepository.save(new Bet("1", game2, LocalDate.now(), 5, 1));
			betRepository.save(new Bet("2", game2, LocalDate.now(), 5100, 2));
			betRepository.save(new Bet("1", game3, LocalDate.now(), 10050, 2));
			betRepository.save(new Bet("3", game3, LocalDate.now(), 500505, 2));
		};
	}
	
	@Bean
	CommandLineRunner populateWalletDatabase(WalletRepository walletRepository) {
		return (args) -> {
			System.out.println("Populating WalletDatabase");
			walletRepository.deleteAll();
			walletRepository.save(new Wallet("1", 500));
			walletRepository.save(new Wallet("2", 50000));
			walletRepository.save(new Wallet("3", 10));
		};
	}
	
	@Bean
	CommandLineRunner testBetQueries(BettableGameRepository bettableGameRepository, BetRepository betRepository) {
		return (args) -> {
			System.out.println("Printing all bets:");
			betRepository.findAll().forEach(System.out::println);
			
			System.out.println("Printing all BettableGames: ");
			bettableGameRepository.findAll().forEach(System.out::println);
			
			System.out.println("Printing all bets from user 1:");
			betRepository.findBetsByPlacedByMember("1").forEach(System.out::println);
			
			System.out.println("Printing all bets from user 2:");
			betRepository.findBetsByPlacedByMember("2").forEach(System.out::println);
			
			System.out.println("Printing all bets from user 3:");
			betRepository.findBetsByPlacedByMember("3").forEach(System.out::println);
			
			System.out.println("Printing all bets for game 1:");
			betRepository.findBetsByBettableGameId(1).forEach(System.out::println);
			
			System.out.println("Printing all bets for game 2:");
			betRepository.findBetsByBettableGameId(2).forEach(System.out::println);
			
			System.out.println("Printing all bets for game 3:");
			betRepository.findBetsByBettableGameId(3).forEach(System.out::println);
		};
	}
	
	@Bean
	CommandLineRunner testWalletQueries(WalletRepository walletRepository) {
		return (args) -> {
			System.out.println("Printing all wallets:");
			walletRepository.findAll().forEach(System.out::println);
			
			System.out.println("Printing wallet from member 1:");
			System.out.println(walletRepository.findByOwnerId("1"));
			
		};
	}

}
