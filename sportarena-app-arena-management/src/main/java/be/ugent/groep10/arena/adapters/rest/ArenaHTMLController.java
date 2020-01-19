package be.ugent.groep10.arena.adapters.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import be.ugent.groep10.arena.adapters.messaging.ArenaGateway;
import be.ugent.groep10.arena.persistence.GameRepository;

@Controller
public class ArenaHTMLController {

	Logger logger = LoggerFactory.getLogger(ArenaRestController.class);
	
	public ArenaHTMLController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/arena")
	public String index() {
		return "arena";
	}
}
