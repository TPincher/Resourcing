package projects.resourcing.credentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/credentials")
public class CredentialsController {

	@Autowired
	private CredentialsService credentialsService;
	
	@PostMapping
	public ResponseEntity<Credentials> createCredentials(@Valid @RequestBody CreateCredentialsDTO data) {
		Credentials createdCredentials = this.credentialsService.createCredentials(data);
	return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
