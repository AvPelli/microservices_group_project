package be.ugent.groep10.authorization.domain;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;
import com.okta.sdk.resource.group.Group;
import com.okta.sdk.resource.user.User;
import com.okta.sdk.resource.user.UserBuilder;

import be.ugent.groep10.authorization.adapters.messaging.RegisterResponse;

@Service
public class AuthorizationService {

	private Client oktaClient;
	private final RegisterSaga registerSaga;

	@Autowired
	public AuthorizationService(RegisterSaga registerSaga) {
		this.oktaClient = Clients.builder() // TODO: put this in some kind of config file
				.setOrgUrl("https://dev-883704.okta.com")
				.setClientCredentials(new TokenClientCredentials("00mLipZ_DVh-Qwo82Gl90uOTNUTPsm1wTLeTGPI-XM")).build();
		this.registerSaga = registerSaga;
	}

	public User makeOktaUser(RegisterRequest registerRequest) {
		Group group = oktaClient.listGroups(registerRequest.getRole(), "", "").single();
		
		//TODO: check if user already exists

		User user = UserBuilder.instance().setEmail(registerRequest.getEmailAddress())
				.setFirstName(registerRequest.getFirstName()).setLastName(registerRequest.getLastName())
				.setPassword("Password123".toCharArray())
				.setGroups(Stream.of(group.getId()).collect(Collectors.toSet())).buildAndCreate(oktaClient);

		return user;
	}

	public boolean removeOktaUser(String userId) {
		User u = oktaClient.getUser(userId);
		return removeOktaUser(u);
	}
	
	public boolean removeOktaUser(User oktaUser) {
		System.out.println("removeUser: " + oktaClient.toString() + oktaUser.toString());
		oktaUser.deactivate();
		oktaUser.delete();
		return true;
	}

	public void register(User user, RegisterRequest registerRequest) {
		System.out.println("Service: register received");
		switch (registerRequest.getRole()) {
		case Role.MEMBER:
			addMember(user, registerRequest);
		case Role.STAFF:
//				return "Not implemented";
		case Role.CATERINGSERVICE:
			addCatering(user, registerRequest);
		case Role.CLUB:
//				return "Not implemented";
		}
	}

	public void registrationTimeout(User oktaUser, String role) {
		switch (role) {
		case Role.MEMBER:
			this.registerSaga.registerMemberTimeout(oktaUser.getId());
		case Role.STAFF:
//			return "Not implemented";
		case Role.CATERINGSERVICE:
			this.registerSaga.registerCateringTimeout(oktaUser.getId());
		case Role.CLUB:
//			return "Not implemented";

		}
		this.removeOktaUser(oktaUser);
	}

	public void registerAuthorizationListener(AuthorizationRegisterListener authorizationRegisterListener) {
		this.registerSaga.registerListener(authorizationRegisterListener);
	}

	private void addMember(User user, RegisterRequest registerRequest) {
		Member member = new Member(user.getId(), registerRequest.getFirstName(), registerRequest.getLastName(),
				registerRequest.getDateOfBirth(), LocalDate.now(), registerRequest.getBankAccountNumber());

		this.registerSaga.startMemberRegisterSaga(member);

//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		
//		HttpEntity<Member> entity = new HttpEntity<>(member, headers);
//		
//		//Member res = restTemplate.postForEntity("http://localhost:2227/member", entity, Member.class).getBody();
//		Member res = restTemplate.postForEntity("http://membermanagement:2227/member", entity, Member.class).getBody();
	}

	private void addCatering(User user, RegisterRequest registerRequest) {
		CateringCompany catering = new CateringCompany(user.getId(), registerRequest.getFirstName(), registerRequest.getLastName(),
				registerRequest.getCompanyName());
		this.registerSaga.startCateringRegisterSage(catering);
	}
	
	public void registerComplete(RegisterResponse registerResponse) {
		this.registerSaga.registerComplete(registerResponse);
	}

	public void registerFailed(RegisterResponse registerResponse) {
		// remove okta user
		this.removeOktaUser(registerResponse.getUserId());
		this.registerSaga.registerFailed(registerResponse);
	}

}
