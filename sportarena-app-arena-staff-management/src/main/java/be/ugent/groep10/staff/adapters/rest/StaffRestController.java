package be.ugent.groep10.staff.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.groep10.staff.domain.Staff;
import be.ugent.groep10.staff.domain.StaffService;
import be.ugent.groep10.staff.persistence.StaffRepository;

@RestController
@RequestMapping("staff")
public class StaffRestController {
	private final StaffRepository staffRepository;
	private final StaffService staffService;
	
	@Autowired
	public StaffRestController(StaffRepository staffRepository, StaffService staffService) {
		this.staffRepository = staffRepository;
		this.staffService = staffService;
	}
	
	@GetMapping
	public Iterable<Staff> getAllMembers(){
		System.out.println("--------------------Find all staff--------------------");
		return this.staffRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Staff getMember(@PathVariable("id") String id) {
		return this.staffRepository.findById(id).orElse(null);
	}
	
	@PostMapping()
	public Staff addStaff(@RequestBody Staff staff){
		Staff newStaff = staffService.createStaff(staff);
		return newStaff;
		
	}
}
