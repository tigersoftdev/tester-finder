package pl.tigersoft.testerfinder.data;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tigersoft.testerfinder.data.model.Device;
import pl.tigersoft.testerfinder.data.model.TesterDto;

@RequiredArgsConstructor
@Service
public class TesterFinderService {

	private final TesterRepository testerRepository;
	private final DeviceRepository deviceRepository;

	// TODO - find better alternative to build a query without countries/devices given
	public List<TesterDto> findTesters(List<String> countries, List<Long> devicesIds) {

		List<String> countriesParam = countries.isEmpty() ? getCountries() : countries;
		List<Long> devicesParam = devicesIds.isEmpty() ? deviceRepository.findAllDevicesIds() : devicesIds;
		return testerRepository.findTestersByCountriesAndDevices(countriesParam, devicesParam);
	}

	public List<Device> getAllDevices() {
		return deviceRepository.findAll();
	}

	public List<String> getCountries() {
		return testerRepository.findCountries();
	}

}
