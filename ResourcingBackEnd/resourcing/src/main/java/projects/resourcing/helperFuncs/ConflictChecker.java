package projects.resourcing.helperFuncs;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import projects.resourcing.job.Job;
import projects.resourcing.temp.Temp;

public class ConflictChecker {

	public static ResponseEntity<List<Temp>> checkDates(Job job, List<Temp> allTemps) {
	    
	    List<Temp> availableTemps = allTemps.stream()
	        .filter(temp -> temp.getJobs().stream()
	            .noneMatch(tempJob -> tempJob.getStartDate().equals(job.getStartDate()) &&
	                (tempJob.getEndDate().after(job.getStartDate()) || tempJob.getStartDate().before(job.getEndDate()))))
	        .collect(Collectors.toList());
	    
	    return new ResponseEntity<>(availableTemps, HttpStatus.OK);
	}
}