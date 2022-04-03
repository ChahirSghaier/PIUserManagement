package sghaier.chahir.piusermanagement.User;

import java.util.List;



public interface IUserManagement {
	public List<User> getUsers();
	public User findByEmail(String email);
	public void deleteUser(String email);
	public void updateUser(User user);
	List<User> findByFirstNameContains(String firstName);
	List<User> findByFirstNameOrLastNameContains(String firstName,String lastName);
	List<User> findByLocked(Boolean locked);
    int unlockedAppUser(String email);
    int lockedAppUser(String email);
    List<Object[]> countTotalUsersByYear();

}
