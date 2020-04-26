package pl.tigersoft.testerfinder.batch;

import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.tigersoft.testerfinder.Constants.PropertyNames;
import pl.tigersoft.testerfinder.Constants.Steps;
import pl.tigersoft.testerfinder.data.model.TesterDevice;

@Component
public class TesterDeviceImportStepBuilder extends ImportStepBuilder<TesterDevice> {

	private static final String[] properties = new String[]{PropertyNames.TESTER, PropertyNames.DEVICE};
	private static final String INSERT_PATTERN = "INSERT INTO tester_device (tester_id, device_id) VALUES (%s, %s);";

	private final JdbcTemplate jdbcTemplate;

	public TesterDeviceImportStepBuilder(
		@Value("${import.file.testerDevice}") Resource resource,
		StepBuilderFactory stepBuilderFactory,
		JdbcTemplate jdbcTemplate) {

		super(stepBuilderFactory, Steps.TESTERS_DEVICES, resource, properties, TesterDevice.class, null);
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	protected ItemWriter<TesterDevice> getItemWriter() {
		return items -> items
			.forEach(item -> jdbcTemplate.execute(String.format(INSERT_PATTERN, item.getTester(), item.getDevice()
			)));
	}
}
