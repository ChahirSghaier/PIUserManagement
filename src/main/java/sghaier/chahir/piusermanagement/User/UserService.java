package sghaier.chahir.piusermanagement.User;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sghaier.chahir.piusermanagement.registre.token.ConfirmationToken;
import sghaier.chahir.piusermanagement.registre.token.ConfirmationTokenService;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepo appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = appUserRepository.findByEmail(email).orElse(null);

		if(user == null  ){
			throw new UsernameNotFoundException("User not found in the data base");
		}
		if(!user.getEnabled() || user.getLocked()) {
			throw new UsernameNotFoundException("You need To Confirm your email");
		
		}
		List<SimpleGrantedAuthority> authorities = getUserAuthority(user.getAppUserRole().name());
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authorities);
	}
	
	
	private List<SimpleGrantedAuthority> getUserAuthority(String userRoles) {
		Set<SimpleGrantedAuthority> roles = new HashSet<SimpleGrantedAuthority>();
		
		roles.add(new SimpleGrantedAuthority(userRoles)); 
		List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}
	
   
	

    public String signUpUser(User appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();


        if (userExists) {
        	User u = appUserRepository.findByEmail(appUser.getEmail()).orElse(null);
        	if(u.getEnabled()==false){
        		appUserRepository.save(u);
                String token = UUID.randomUUID().toString();

                ConfirmationToken confirmationToken = new ConfirmationToken(
                		token,
                		LocalDateTime.now(),
                		LocalDateTime.now().plusMinutes(15),
                		u

                		);
                confirmationTokenService.saveConfirmationToken(confirmationToken);
                return "email already taken";
        	}else{
        		return "email already taken";
        	}
            
        }
    

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
        		token,
        		LocalDateTime.now(),
        		LocalDateTime.now().plusMinutes(15),
        		appUser
        		
        		);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return "";
    }
    
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
