package sghaier.chahir.piusermanagement.User;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserManagement implements IUserManagement{


	@Autowired
	private UserRepo myRepository;


	@Override
	public List<User> getUsers() {
		return myRepository.findAll(Sort.by(Sort.Direction.DESC, "firstName"));
	}

	@Override
	public User findByEmail(String email) {
		return myRepository.findByEmail(email).orElse(null);
	}

	@Override
	public void deleteUser(String email) {
		User u = myRepository.findByEmail(email).orElse(null);
		myRepository.delete(u);
	}

	@Override
	public void updateUser(User user) {
		myRepository.save(user);

	}

	@Override
	public List<User> findByFirstNameContains(String firstName) {
		return myRepository.findByFirstNameContains(firstName);
	}

	@Override
	public List<User> findByFirstNameOrLastNameContains(String firstName, String lastName) {
		return myRepository.findByFirstNameOrLastNameContains(firstName, lastName);
	}

	@Override
	public List<User> findByLocked(Boolean locked) {
		return myRepository.findByLocked(locked);
	}

	@Override
	public int unlockedAppUser(String email) {
		return myRepository.unlockedAppUser(email);
	}

	@Override
	public int lockedAppUser(String email) {
		return myRepository.lockedAppUser(email);
	}

	@Override
	public List<Object[]> countTotalUsersByYear() {
		return myRepository.countTotalUsersByYear();
	}


}
