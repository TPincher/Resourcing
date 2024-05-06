package projects.resourcing.job;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import projects.resourcing.temp.Temp;

@Entity
@Table(name = "jobs")
public class Job {

	public Job() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date endDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "temp_id")
	private Temp temp;

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

	public Temp getTemp() {
		return temp;
	}

	public void setTemp(Temp temp) {
		this.temp = temp;
	}
	
	
}
