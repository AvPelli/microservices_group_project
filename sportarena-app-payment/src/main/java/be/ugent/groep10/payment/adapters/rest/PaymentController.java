package be.ugent.groep10.payment.adapters.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentController {
	
	@GetMapping("payment")
	public String Payment() {
		return "payment";
	}
}
