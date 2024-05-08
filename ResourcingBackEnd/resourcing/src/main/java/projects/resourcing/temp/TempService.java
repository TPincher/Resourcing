package projects.resourcing.temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import projects.resourcing.job.Job;
import projects.resourcing.job.JobRepository;

@Service
@Transactional
public class TempService {
	
	@Autowired
	private TempRepository repo;
	
	@Autowired
	private JobRepository jobRepo;

	public Temp createTemp(@Valid CreateTempDTO data) {
		Temp newTemp = new Temp();
			newTemp.setFirstName(data.getFirstName());
			newTemp.setLastName(data.getLastName());
		
		return this.repo.save(newTemp);
	}

	public List<Temp> getAll() {
		return this.repo.findAll();
	}
	
	public List<Temp> getFreeTemps(Long jobId) {
	    Optional<Job> foundJob = jobRepo.findById(jobId);

	    if (foundJob.isPresent()) {
	        Job job = foundJob.get();
	        Date startDate = job.getStartDate();
	        Date endDate = job.getEndDate();

	        List<Long> tempIds = repo.findJobsBetweenDates(startDate, endDate);

	        List<Temp> temps = new ArrayList<>();
	        for (Long tempId : tempIds) {
	            Optional<Temp> tempOptional = repo.findById(tempId);
	            tempOptional.ifPresent(temps::add);
	        }

	        return temps;
	    } else {
	    	return Collections.emptyList();
	    }
	}

	public Optional<Temp> findById(Long id) {
		return this.repo.findById(id);
	}

}
