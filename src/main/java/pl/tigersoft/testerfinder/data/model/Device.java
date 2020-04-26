package pl.tigersoft.testerfinder.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
public class Device {

	@Id
	private Long id;
	private String description;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "tester_device",
		joinColumns = @JoinColumn(name = "device_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "tester_id", referencedColumnName = "id")
	)
	private Set<Tester> testers;

	@JsonIgnore
	@OneToMany(mappedBy = "device")
	private Set<Bug> bugs;

}
