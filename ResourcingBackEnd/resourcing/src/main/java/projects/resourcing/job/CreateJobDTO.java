package projects.resourcing.job;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;

public class CreateJobDTO {
	
	@NotBlank
	private String name;
	
	private Date startDate;
	
	private Date endDate;
	
	private Long assignedTemp;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getAssignedTemp() {
		return assignedTemp;
	}

	public void setAssignedTemp(Long assignedTemp) {
		this.assignedTemp = assignedTemp;
	}
	
	
}
