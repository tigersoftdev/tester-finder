package pl.tigersoft.testerfinder.batch.support;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.StringUtils;
import pl.tigersoft.testerfinder.Constants;
import pl.tigersoft.testerfinder.Constants.Strings;

public class TimePropertyEditor extends PropertyEditorSupport {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN);

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isNotEmpty(text)) {
			setValue(LocalDateTime.parse(text, formatter));
		} else {
			setValue(null);
		}
	}

	@Override
	public String getAsText() throws IllegalArgumentException {
		Object date = getValue();
		return date == null ? Strings.EMPTY : formatter.format((LocalDateTime) date);
	}

}
