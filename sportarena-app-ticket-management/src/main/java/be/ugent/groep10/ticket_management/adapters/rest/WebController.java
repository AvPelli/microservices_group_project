package be.ugent.groep10.ticket_management.adapters.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tickets")
public class WebController {
	
	Logger logger = LoggerFactory.getLogger(WebController.class);

	@GetMapping("shop")
	public String shop() {
		return "shop";
	}

}
