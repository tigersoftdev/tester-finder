package pl.tigersoft.testerfinder.batch;

import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import pl.tigersoft.testerfinder.Constants.PropertyNames;
import pl.tigersoft.testerfinder.Constants.Steps;
import pl.tigersoft.testerfinder.data.BugRepository;
import pl.tigersoft.testerfinder.data.model.Bug;

@Component
public class BugImportStepBuilder extends ImportStepBuilder<Bug> {

	private static final String[] properties = new String[]{PropertyNames.ID, PropertyNames.DEVICE,
		PropertyNames.TESTER};

	public BugImportStepBuilder(
		@Value("${import.file.bugs}") Resource resource,
		StepBuilderFactory stepBuilderFactory,
		BugRepository bugRepository) {

		super(stepBuilderFactory, Steps.BUGS, resource, properties, Bug.class, bugRepository);
	}

}
