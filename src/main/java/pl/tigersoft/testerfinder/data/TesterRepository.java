package pl.tigersoft.testerfinder.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.tigersoft.testerfinder.data.model.Tester;
import pl.tigersoft.testerfinder.data.model.TesterDto;

public interface TesterRepository extends JpaRepository<Tester, Long>, JpaSpecificationExecutor<Tester> {

	@Query("SELECT DISTINCT country FROM Tester")
	List<String> findCountries();

	@Query("SELECT new pl.tigersoft.testerfinder.data.model.TesterDto(t.firstName, t.lastName, count(b.id)) "
		+ "FROM Bug b "
		+ "LEFT JOIN Tester t ON b.tester.id=t.id "
		+ "LEFT JOIN Device d ON b.device.id=d.id "
		+ "WHERE t.country IN :countries AND d.id IN :devices GROUP BY t.id")
	List<TesterDto> findTestersByCountriesAndDevices(@Param("countries") List<String> countries,
		@Param("devices") List<Long> devicesIds);

}
