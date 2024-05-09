package projects.resourcing.job;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import projects.resourcing.exceptions.ServiceValidationException;
import projects.resourcing.exceptions.ValidationErrors;
import projects.resourcing.temp.Temp;
import projects.resourcing.temp.TempService;

@Service
@Transactional
public class JobService {
	
	@Autowired
	private JobRepository repo;
	
	@Autowired
	private TempService tempService;

	public Job createJob(@Valid CreateJobDTO data) throws ServiceValidationException {
		Job newJob = new Job();
		ValidationErrors errors = new ValidationErrors();
		
			newJob.setName(data.getName());
			newJob.setStartDate(data.getStartDate());
			if (data.getEndDate().before(data.getStartDate())) {	
				errors.addError("Date Format", String.format("End date must be the same as, or later than, the start date"));
			} else {
				newJob.setEndDate(data.getEndDate());
			}
			if (errors.hasErrors()) {
				throw new ServiceValidationException(errors);
			}
		return this.repo.save(newJob);
	}

	public List<Job> getAll() {
		return this.repo.findAll();
	}

	public List<Job> getFilteredJobs(Boolean assigned) {
			
        List<Job> filteredList = new ArrayList<>();
		
        if (Boolean.TRUE.equals(assigned)) {
			List<Long> filterIds = repo.filterNotNullJobs();
			for (Long filterId : filterIds) {
				Optional<Job> jobOptional = repo.findById(filterId);
				jobOptional.ifPresent(filteredList::add);
			}
			return filteredList;
		} else if (Boolean.FALSE.equals(assigned)) {
			List<Long> filterIds = repo.filterNullJobs();
			for (Long filterId : filterIds) {
				Optional<Job> jobOptional = repo.findById(filterId);
				jobOptional.ifPresent(filteredList::add);
			}
			return filteredList;
		} else {
	    	return Collections.emptyList();
		}
	}

	public Optional<Job> findById(Long id) {
		return this.repo.findById(id);
	}

	public Optional<Job> updateById(@Valid UpdateJobDTO data, Long id) throws ServiceValidationException {
		Optional<Job> maybeJob = this.findById(id);
			if(maybeJob.isEmpty()) {
				return maybeJob;
			}
			
			Job foundJob = maybeJob.get();
			ValidationErrors errors = new ValidationErrors();
			Long tempId = data.getAssignedTemp();
			
			if(data.getName() != null) {
				foundJob.setName(data.getName());
			}
			if(data.getStartDate() != null) {
				foundJob.setStartDate(data.getStartDate());
			}
			if(data.getEndDate() != null) {
				
				if (data.getEndDate().before(data.getStartDate())) {	
					errors.addError("Date Format", String.format("End date must be the same as, or later than, the start date"));
				} else {
					foundJob.setEndDate(data.getEndDate());
				}
			}

			Optional<Temp> maybeTemp = this.tempService.findById(tempId);
			if (tempId == -1) {
			    foundJob.setTemp(null);
			}
			else if(maybeTemp.isEmpty() ) {
				errors.addError("Temp", String.format("Temp with id %s does not exist", tempId));
			} else {
				foundJob.setTemp(maybeTemp.get());
			}

			if (errors.hasErrors()) {
				throw new ServiceValidationException(errors);
			}
			
			Job updatedJob = this.repo.save(foundJob);
			return Optional.of(updatedJob);
	}

	public boolean deleteJobById(Long id) {
		Optional<Job> maybeJob = this.repo.findById(id);
		if(maybeJob.isEmpty()) {
			return false;
		}
		this.repo.delete(maybeJob.get());
		return true;
	}
}
