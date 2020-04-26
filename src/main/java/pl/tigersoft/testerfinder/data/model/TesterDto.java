package pl.tigersoft.testerfinder.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TesterDto {

	private String firstName;
	private String lastName;
	private long bugs;

}
