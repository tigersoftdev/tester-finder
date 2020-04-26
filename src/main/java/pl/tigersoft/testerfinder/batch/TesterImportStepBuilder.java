package pl.tigersoft.testerfinder.batch;

import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import pl.tigersoft.testerfinder.Constants.PropertyNames;
import pl.tigersoft.testerfinder.Constants.Steps;
import pl.tigersoft.testerfinder.data.TesterRepository;
import pl.tigersoft.testerfinder.data.model.Tester;

@Component
public class TesterImportStepBuilder extends ImportStepBuilder<Tester> {

	private static final String[] properties = new String[]{PropertyNames.ID, PropertyNames.FIRST_NAME,
		PropertyNames.LAST_NAME, PropertyNames.COUNTRY,
		PropertyNames.LAST_LOGIN};

	public TesterImportStepBuilder(
		@Value("${import.file.testers}") Resource resource,
		StepBuilderFactory stepBuilderFactory,
		TesterRepository testerRepository) {

		super(stepBuilderFactory, Steps.TESTERS, resource, properties, Tester.class, testerRepository);
	}

}
