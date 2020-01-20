package be.ugent.groep10.gamblings.adapters;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GamblingController {
	
	@RequestMapping("/gambling/")
	public String index() {
		return "gamblingDummy";
	}

}
