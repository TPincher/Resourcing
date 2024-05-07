package projects.resourcing.temp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class TempService {
	
	@Autowired
	private TempRepository repo;

	public Temp createTemp(@Valid CreateTempDTO data) {
		Temp newTemp = new Temp();
			newTemp.setFirstName(data.getFirstName());
			newTemp.setLastName(data.getLastName());
		
		return this.repo.save(newTemp);
	}

	public List<Temp> getAll() {
		return this.repo.findAll();
	}

	public Optional<Temp> findById(Long id) {
		return this.repo.findById(id);
	}

}
