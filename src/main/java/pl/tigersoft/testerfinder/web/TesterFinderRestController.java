package pl.tigersoft.testerfinder.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.tigersoft.testerfinder.Constants.Params;
import pl.tigersoft.testerfinder.Constants.Strings;
import pl.tigersoft.testerfinder.data.model.Device;
import pl.tigersoft.testerfinder.data.model.TesterDto;
import pl.tigersoft.testerfinder.data.TesterFinderService;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TesterFinderRestController {

	private final TesterFinderService finderService;

	@GetMapping("/devices")
	public List<Device> deviceList() {
		return finderService.getAllDevices();
	}

	@GetMapping("/countries")
	public List<String> countryList() {
		return finderService.getCountries();
	}

	@GetMapping("/testers")
	public List<TesterDto> searchTesters(
		@RequestParam(value = Params.COUNTRIES, defaultValue = Strings.EMPTY) List<String> countries,
		@RequestParam(value = Params.DEVICES_IDS, defaultValue = Strings.EMPTY) List<Long> devicesIds) {
		return finderService.findTesters(countries, devicesIds);
	}

}
