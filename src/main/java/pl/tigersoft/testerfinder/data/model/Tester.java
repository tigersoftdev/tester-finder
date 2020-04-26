package pl.tigersoft.testerfinder.data.model;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tester {

	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private String country;
	private LocalDateTime lastLogin;

	@ManyToMany(mappedBy = "testers")
	private Set<Device> devices;

	@OneToMany(mappedBy = "tester")
	private Set<Bug> bugs;

}

