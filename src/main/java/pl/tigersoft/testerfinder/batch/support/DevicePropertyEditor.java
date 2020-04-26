package pl.tigersoft.testerfinder.batch.support;

import java.beans.PropertyEditorSupport;
import org.apache.commons.lang3.StringUtils;
import pl.tigersoft.testerfinder.Constants.Strings;
import pl.tigersoft.testerfinder.data.model.Device;

public class DevicePropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isNotEmpty(text)) {
			setValue(Device.builder().id(Long.parseLong(text)).build());
		} else {
			setValue(null);
		}
	}

	@Override
	public String getAsText() throws IllegalArgumentException {
		Object device = getValue();
		return device == null ? Strings.EMPTY : Long.toString(((Device) device).getId());
	}

}
