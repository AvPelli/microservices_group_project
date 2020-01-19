package be.ugent.groep10.staff.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.ugent.groep10.staff.domain.Staff;

@Repository
public interface StaffRepository extends CrudRepository<Staff, String>{

}
