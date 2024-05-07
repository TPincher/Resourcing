package projects.resourcing.job;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import projects.resourcing.exceptions.NotFoundException;
import projects.resourcing.exceptions.ServiceValidationException;

@RestController
@RequestMapping("/jobs")
public class JobController {

	@Autowired
	private JobService jobService;
	
	@PostMapping
	public ResponseEntity<Job> createUser(@Valid @RequestBody CreateJobDTO data) throws ServiceValidationException {
	Job createdJob = this.jobService.createJob(data);
	return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
	}
	
	
	private ResponseEntity<List<Job>> getAssignedJobs() {
	    List<Job> allJobs = this.jobService.getAll();
	    List<Job> assignedJobs = allJobs.stream()
	                                    .filter(job -> job.getTemp() != null)
	                                    .collect(Collectors.toList());
	    
	    return new ResponseEntity<>(assignedJobs, HttpStatus.OK);
	}
	
	private ResponseEntity<List<Job>> getNotAssignedJobs() {
	    List<Job> allJobs = this.jobService.getAll();
	    List<Job> notAssignedJobs = allJobs.stream()
	                                    .filter(job -> job.getTemp() == null)
	                                    .collect(Collectors.toList());
	    
	    return new ResponseEntity<>(notAssignedJobs, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Job>> handleRequest(@RequestParam(value = "assigned", required = false) Boolean assigned) {
	    if (assigned != null && assigned) {
	        return getAssignedJobs();
	    } else if (assigned != null && !assigned) {
	        return getNotAssignedJobs();
	    } else {
	        List<Job> allJobs = this.jobService.getAll();
	        return new ResponseEntity<>(allJobs, HttpStatus.OK);
	    }
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) throws NotFoundException {
		Optional<Job> maybeJob = this.jobService.findById(id);
		Job foundJob = maybeJob.orElseThrow(() -> new NotFoundException(Job.class, id));
		return new ResponseEntity<>(foundJob, HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Job> updateJobById(@Valid @RequestBody UpdateJobDTO data, @PathVariable Long id) throws NotFoundException, ServiceValidationException {
		Optional<Job> maybeUpdatedJob = this.jobService.updateById(data, id);
		Job updatedJob = maybeUpdatedJob.orElseThrow(() -> new NotFoundException(Job.class, id));
		return new ResponseEntity<>(updatedJob, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Job> deleteJobById(@PathVariable Long id) throws NotFoundException {
		boolean deleted = this.jobService.deleteJobById(id);
		if(!deleted) {
			throw new NotFoundException(Job.class, id);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
}
