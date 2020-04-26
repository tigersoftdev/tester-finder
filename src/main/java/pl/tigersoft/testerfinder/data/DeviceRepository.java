package pl.tigersoft.testerfinder.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import pl.tigersoft.testerfinder.data.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long>, JpaSpecificationExecutor<Device> {

	@Query("SELECT DISTINCT id FROM Device")
	List<Long> findAllDevicesIds();

}
