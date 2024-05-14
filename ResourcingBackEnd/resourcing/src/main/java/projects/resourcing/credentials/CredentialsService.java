package projects.resourcing.credentials;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class CredentialsService implements UserDetailsService{

	@Autowired
	private CredentialsRepository repo;

	public Credentials createCredentials(@Valid CreateCredentialsDTO data) {
	    Credentials loggedCredentials = new Credentials();
	    loggedCredentials.setUserId(data.getUserId());
	    loggedCredentials.setUsername(data.getUsername());
	    loggedCredentials.setRole("TEMP");
	    
	    String passwordToHash = data.getPassword();
	    
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(passwordToHash);
	    
	    loggedCredentials.setPassword(encodedPassword);

	    return this.repo.save(loggedCredentials);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Credentials> credentials = repo.findByUsername(username);
		if(credentials.isPresent()) {
			var userObj = credentials.get();
			return User.builder()
					.username(userObj.getUsername())
					.password(userObj.getPassword())
					.roles(getRoles(userObj))
					.build();
					
		} else {
			throw new UsernameNotFoundException(username);
		}
	}
	
	private String[] getRoles(Credentials user) {
		if(user.getRole() == null) {
			return new String[]{"TEMP"};
		}
		return user.getRole().split(",");
		
	}
}
