package projects.resourcing.temp;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TempRepository extends JpaRepository<Temp, Long> {

    @Query(value = "SELECT DISTINCT id FROM temps t WHERE t.id NOT IN " 
    				+ "(SELECT COALESCE(temp_id, -1) FROM jobs j WHERE "
    					+ "(:startDate BETWEEN j.start_date AND j.end_date) OR "
    					+ "(:endDate BETWEEN j.start_date AND j.end_date))", nativeQuery = true)
    
    List<Long> findJobsBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
