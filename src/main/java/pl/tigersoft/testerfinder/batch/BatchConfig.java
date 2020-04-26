package pl.tigersoft.testerfinder.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tigersoft.testerfinder.Constants;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Bean
	public Job initialImportJob(JobBuilderFactory jobBuilderFactory,
		DeviceImportStepBuilder deviceImportStepBuilder,
		TesterImportStepBuilder testerImportStepBuilder,
		BugImportStepBuilder bugImportStepBuilder,
		TesterDeviceImportStepBuilder testerDeviceImportStepBuilder) {

		Step devicesStep = deviceImportStepBuilder.build();
		Step testersStep = testerImportStepBuilder.build();
		Step bugsStep = bugImportStepBuilder.build();
		Step testersDevicesStep = testerDeviceImportStepBuilder.build();

		return jobBuilderFactory
			.get(Constants.IMPORT_JOB_NAME)
			.start(devicesStep)
			.next(testersStep)
			.next(bugsStep)
			.next(testersDevicesStep)
			.build();
	}

}
