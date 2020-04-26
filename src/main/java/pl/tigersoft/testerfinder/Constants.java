package pl.tigersoft.testerfinder;

public interface Constants {

	int CHUNK_SIZE = 100;

	String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	String SAVE_METHOD_NAME = "save";
	String IMPORT_JOB_NAME = "data-load";

	interface PropertyNames {

		String ID = "id";
		String DESCRIPTION = "description";
		String FIRST_NAME = "firstName";
		String LAST_NAME = "lastName";
		String COUNTRY = "country";
		String LAST_LOGIN = "lastLogin";
		String DEVICE = "device";
		String TESTER = "tester";
	}

	interface Steps {

		String DEVICES = "devices";
		String TESTERS = "testers";
		String BUGS = "bugs";
		String TESTERS_DEVICES = "testersDevices";
	}

	interface Params {

		String DEVICES_IDS = "devices_ids";
		String COUNTRIES = "countries";
	}

	interface Strings {

		String EMPTY = "";
	}
}
