package pl.tigersoft.testerfinder.batch;

import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import pl.tigersoft.testerfinder.Constants.PropertyNames;
import pl.tigersoft.testerfinder.Constants.Steps;
import pl.tigersoft.testerfinder.data.DeviceRepository;
import pl.tigersoft.testerfinder.data.model.Device;

@Component
public class DeviceImportStepBuilder extends ImportStepBuilder<Device> {

	private static final String[] properties = new String[]{PropertyNames.ID, PropertyNames.DESCRIPTION};

	public DeviceImportStepBuilder(
		@Value("${import.file.devices}") Resource resource,
		StepBuilderFactory stepBuilderFactory,
		DeviceRepository deviceRepository) {

		super(stepBuilderFactory, Steps.DEVICES, resource, properties, Device.class, deviceRepository);
	}

}
