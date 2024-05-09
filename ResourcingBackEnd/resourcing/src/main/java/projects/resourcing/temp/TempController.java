package projects.resourcing.temp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import projects.resourcing.exceptions.NotFoundException;

@RestController
@RequestMapping("/temps")
public class TempController {

	@Autowired
	private TempService tempService;
	
	@PostMapping
	public ResponseEntity<Temp> createUser(@Valid @RequestBody CreateTempDTO data) {
		Temp createdTemp = this.tempService.createTemp(data);
	return new ResponseEntity<>(createdTemp, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Temp>> getAllTemps(@RequestParam(value = "jobId", required = false) Long jobId) {
	    if (jobId != null && jobId > 0) {
	        List<Temp> freeTemps = this.tempService.getFreeTemps(jobId);
	        return new ResponseEntity<>(freeTemps, HttpStatus.OK);
	    } else {
	        List<Temp> allTemps = this.tempService.getAll();
	        return new ResponseEntity<>(allTemps, HttpStatus.OK);
	    }
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Temp> getTempById(@PathVariable Long id) throws NotFoundException {
		Optional<Temp> maybeTemp = this.tempService.findById(id);
		Temp foundTemp = maybeTemp.orElseThrow(() -> new NotFoundException(Temp.class, id));
		return new ResponseEntity<>(foundTemp, HttpStatus.OK);
	}
	
}
