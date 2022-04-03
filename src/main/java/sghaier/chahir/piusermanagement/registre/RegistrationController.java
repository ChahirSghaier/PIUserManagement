package sghaier.chahir.piusermanagement.registre;

import lombok.AllArgsConstructor;
import sghaier.chahir.piusermanagement.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "registration")
@AllArgsConstructor
@CrossOrigin
public class RegistrationController {

    private final RegistrationService registrationService;

    // http://localhost:8089/WomenEmpowerment/registration
    @PostMapping
    public ResponseEntity<?> register(@RequestBody User request) {
    	String a = registrationService.register(request);
    	if(a =="") {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    	 
    }
    
    // http://localhost:8089/WomenEmpowerment/registration/confirm
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
    
    
    // http://localhost:8089/WomenEmpowerment/registration/forgetpassword
    @GetMapping("/forgetpassword/{email}")
    public String forgetpassword(@PathVariable("email") String email) {
        return registrationService.forgetpassword(email);
    }
    
    
    // http://localhost:8089/WomenEmpowerment/registration/reset
    @GetMapping("/reset/{token}/{email}/{password}")
    public String reset(@PathVariable("token") String token,@PathVariable("email") String email,@PathVariable("password") String password) {
        return registrationService.resetPassword(token,email,password);
    }


}
