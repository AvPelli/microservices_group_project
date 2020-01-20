package be.ugent.groep10.gamblings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import be.ugent.groep10.gamblings.adapters.messaging.Channels;
import be.ugent.groep10.gamblings.domain.Bet;
import be.ugent.groep10.gamblings.domain.BettableGame;
import be.ugent.groep10.gamblings.domain.Prediction;
import be.ugent.groep10.gamblings.domain.Wallet;
import be.ugent.groep10.gamblings.persistence.BetRepository;
import be.ugent.groep10.gamblings.persistence.BettableGameRepository;
import be.ugent.groep10.gamblings.persistence.WalletRepository;

@EnableBinding(Channels.class)
@SpringBootApplication
public class SportarenaAppGamblingManagementNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppGamblingManagementNewApplication.class, args);
	}

	
	@Bean
	CommandLineRunner populateDatabase(BetRepository betRepository, BettableGameRepository bettableGameRepository, WalletRepository walletRepository) {
		return (args) -> {
			System.out.println("Populating BetDatabase");
			betRepository.deleteAll();
			bettableGameRepository.deleteAll();
			walletRepository.deleteAll();
			
			BettableGame game1 = new BettableGame("m01", LocalDateTime.now().plus(1, ChronoUnit.DAYS));
			BettableGame game2 = new BettableGame("m02", LocalDateTime.now().plus(2, ChronoUnit.DAYS));
			BettableGame game3 = new BettableGame("m03", LocalDateTime.now().plus(3, ChronoUnit.DAYS));
			
			bettableGameRepository.save(game1);
			bettableGameRepository.save(game2);
			bettableGameRepository.save(game3);
			
			
			betRepository.save(new Bet("simeon", "m01", new Prediction(1,0,3.2), 20));
			betRepository.save(new Bet("simeon", "m01", new Prediction(0,0,2.1), 20));
			betRepository.save(new Bet("simeon", "m02", new Prediction(5,4,15.6), 2));
			betRepository.save(new Bet("jasper", "m02", new Prediction(1,0,3.2), 30));
			betRepository.save(new Bet("jasper", "m02", new Prediction(4,3,5.7), 20));
			betRepository.save(new Bet("jasper", "m03", new Prediction(4,1,4.6), 60));
			betRepository.save(new Bet("jasper", "m03", new Prediction(0,0,1.5), 25));
			
			walletRepository.save(new Wallet("simeon", 500));
			walletRepository.save(new Wallet("jasper", 50000));
		};
	}
	
	
	@Bean
	CommandLineRunner testBetQueries(BettableGameRepository bettableGameRepository, BetRepository betRepository, WalletRepository walletRepository) {
		return (args) -> {
			
//			System.out.println("Printing all bets:");
//			betRepository.findAll().forEach(System.out::println);
//			
//			System.out.println("Printing all BettableGames: ");
//			bettableGameRepository.findAll().forEach(System.out::println);
//			
//			System.out.println("Printing all bets from user 1:");
//			betRepository.findBetsByPlacedByMember("1").forEach(System.out::println);
//			
//			System.out.println("Printing all bets from user 2:");
//			betRepository.findBetsByPlacedByMember("2").forEach(System.out::println);
//			
//			System.out.println("Printing all bets from user 3:");
//			betRepository.findBetsByPlacedByMember("3").forEach(System.out::println);
//			
//			System.out.println("Printing all bets for game 1:");
//			betRepository.findBetsByBettableGameId(1).forEach(System.out::println);
//			
//			System.out.println("Printing all bets for game 2:");
//			betRepository.findBetsByBettableGameId(2).forEach(System.out::println);
//			
//			System.out.println("Printing all bets for game 3:");
//			betRepository.findBetsByBettableGameId(3).forEach(System.out::println);
//			
//			System.out.println("Printing all wallets:");
//			walletRepository.findAll().forEach(System.out::println);
//			
//			System.out.println("Printing wallet from member 1:");
//			System.out.println(walletRepository.findByOwnerId("1"));
		};
	}
	
	
	
	
	
	
	
	
	
}
