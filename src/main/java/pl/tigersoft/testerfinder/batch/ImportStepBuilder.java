package pl.tigersoft.testerfinder.batch;

import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.RecordSeparatorPolicy;
import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.validation.DataBinder;
import pl.tigersoft.testerfinder.Constants;
import pl.tigersoft.testerfinder.batch.support.DevicePropertyEditor;
import pl.tigersoft.testerfinder.batch.support.TesterPropertyEditor;
import pl.tigersoft.testerfinder.batch.support.TimePropertyEditor;
import pl.tigersoft.testerfinder.data.model.Device;
import pl.tigersoft.testerfinder.data.model.Tester;

public class ImportStepBuilder<T> {

	private static final RecordSeparatorPolicy recordSeparatorPolicy = new BlankLineRecordSeparatorPolicy();

	private final StepBuilderFactory stepBuilderFactory;
	private final String stepName;
	private final Resource inputResource;
	private final String[] propertyNames;
	private final Class<T> itemClass;
	private final CrudRepository<T, ?> repository;

	public ImportStepBuilder(@NonNull StepBuilderFactory stepBuilderFactory,
		@NonNull String stepName, @NonNull Resource inputResource, @NonNull String[] propertyNames,
		@NonNull Class<T> itemClass, @Nullable CrudRepository<T, ?> repository) {
		this.stepBuilderFactory = stepBuilderFactory;
		this.stepName = stepName;
		this.inputResource = inputResource;
		this.propertyNames = propertyNames;
		this.itemClass = itemClass;
		this.repository = repository;
	}

	public Step build() {
		return stepBuilderFactory
			.get(stepName)
			.<T, T>chunk(Constants.CHUNK_SIZE)
			.reader(getItemReader())
			.writer(getItemWriter())
			.build();
	}

	private FlatFileItemReader<T> getItemReader() {

		FlatFileItemReader<T> itemReader = new FlatFileItemReader<>();
		itemReader.setResource(inputResource);
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(getLineMapper());
		itemReader.setRecordSeparatorPolicy(recordSeparatorPolicy);
		return itemReader;
	}

	private LineMapper<T> getLineMapper() {

		DefaultLineMapper<T> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

		lineTokenizer.setNames(propertyNames);

		BeanWrapperFieldSetMapper<T> fieldSetMapper = new ImportBeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(itemClass);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}

	protected ItemWriter<T> getItemWriter() {
		if (repository == null) {
			return items -> {
			};
		}
		RepositoryItemWriter<T> itemWriter = new RepositoryItemWriter<>();
		itemWriter.setMethodName(Constants.SAVE_METHOD_NAME);
		itemWriter.setRepository(repository);
		return itemWriter;
	}

	private static class BlankLineRecordSeparatorPolicy extends SimpleRecordSeparatorPolicy {

		@Override
		public boolean isEndOfRecord(final String line) {
			return StringUtils.isNotBlank(StringUtils.trimToNull(line));
		}
	}

	private static class ImportBeanWrapperFieldSetMapper<T> extends BeanWrapperFieldSetMapper<T> {

		@Override
		protected void initBinder(DataBinder binder) {
			binder.registerCustomEditor(LocalDateTime.class, new TimePropertyEditor());
			binder.registerCustomEditor(Tester.class, new TesterPropertyEditor());
			binder.registerCustomEditor(Device.class, new DevicePropertyEditor());
		}
	}

}
