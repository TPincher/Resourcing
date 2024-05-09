package projects.resourcing.job;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Long>{
	
    @Query(value = "SELECT DISTINCT id FROM jobs j WHERE j.temp_id IS NULL", nativeQuery = true)
    List<Long> filterNullJobs();
    
    @Query(value = "SELECT DISTINCT id FROM jobs j WHERE j.temp_id IS NOT NULL", nativeQuery = true)
    List<Long> filterNotNullJobs();
}
