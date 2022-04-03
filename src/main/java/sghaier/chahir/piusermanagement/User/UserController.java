package sghaier.chahir.piusermanagement.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8089")
public class UserController {
	
	@Autowired
	IUserManagement userService;
	
	// http://localhost:8089/WomenEmpowerment/user/findByEmail
	@GetMapping("/findByEmail/{email}")
	@ResponseBody
	public User findByEmail(@PathVariable("email") String email){
		return userService.findByEmail(email);
	}
	
	// http://localhost:8089/WomenEmpowerment/user/getUsers
	@GetMapping("/getUsers")
	@ResponseBody
	public ResponseEntity<List<User>> getUsers(){
		return ResponseEntity.ok().body(userService.getUsers());
		
	}
	
	// http://localhost:8089/WomenEmpowerment/user/deleteUser/
	@DeleteMapping("/deleteUser/{email}")
	@ResponseBody
	public void deleteUser(@PathVariable("email") String email){
		userService.deleteUser(email);
		
	}
	
	
	//http://localhost:8089/WomenEmpowerment/user/updateUser
	@PutMapping("/updateUser")
	@ResponseBody
	public void updateUser(@RequestBody User user) {
	userService.updateUser(user);
	}
	
	
	// http://localhost:8089/WomenEmpowerment/user/findByFirstNameContains/
	@GetMapping("/findByFirstNameContains/{firstName}")
	@ResponseBody
	public List<User> findByFirstNameContains(@PathVariable("firstName") String firstName){
		return userService.findByFirstNameContains(firstName);
	}
	
	// http://localhost:8089/WomenEmpowerment/user/findByFirstNameOrLastNameContains/
	@GetMapping("/findByFirstNameOrLastNameContains/{firstName}/{lastName}")
	@ResponseBody
	public List<User> findByFirstNameOrLastNameContains(@PathVariable("firstName") String firstName,@PathVariable("lastName") String lastName){
		return userService.findByFirstNameOrLastNameContains(firstName,lastName);
	}
	
	
	// http://localhost:8089/WomenEmpowerment/user/findByLocked/
	@GetMapping("/findByLocked/{locked}")
	@ResponseBody
	public List<User> findByLocked(@PathVariable("locked") Boolean locked){
		return userService.findByLocked(locked);
	}
	
	// http://localhost:8089/WomenEmpowerment/user/unlockedAppUser/
	@PutMapping("/unlockedAppUser/{email}")
	@ResponseBody
	public int unlockedAppUser(@PathVariable("email") String email){
		return userService.unlockedAppUser(email);
	}
	
	// http://localhost:8089/WomenEmpowerment/user/lockedAppUser/
	@PutMapping("/lockedAppUser/{email}")
	@ResponseBody
	public int lockedAppUser(@PathVariable("email") String email){
		return userService.lockedAppUser(email);
	}
	
	
	// http://localhost:8089/WomenEmpowerment/user/countTotalUsersByYear
	@GetMapping("/countTotalUsersByYear")
	@ResponseBody
	public List<Object[]> countTotalUsersByYear(){
		return userService.countTotalUsersByYear();
	}

	


}
