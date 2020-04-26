package pl.tigersoft.testerfinder.batch.support;

import java.beans.PropertyEditorSupport;
import org.apache.commons.lang3.StringUtils;
import pl.tigersoft.testerfinder.Constants.Strings;
import pl.tigersoft.testerfinder.data.model.Tester;

public class TesterPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isNotEmpty(text)) {
			setValue(Tester.builder().id(Long.parseLong(text)).build());
		} else {
			setValue(null);
		}
	}

	@Override
	public String getAsText() throws IllegalArgumentException {
		Object tester = getValue();
		return tester == null ? Strings.EMPTY : Long.toString(((Tester) tester).getId());
	}

}
