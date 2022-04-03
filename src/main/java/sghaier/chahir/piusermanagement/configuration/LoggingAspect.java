package sghaier.chahir.piusermanagement.configuration;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sghaier.chahir.piusermanagement.User.IUserManagement;
import sghaier.chahir.piusermanagement.User.UserRepo;
import sghaier.chahir.piusermanagement.registre.token.ConfirmationTokenService;
@AllArgsConstructor
@Component
@EnableAsync
@Aspect
public class LoggingAspect {
	private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

	private final ConfirmationTokenService confirmationTokenRepository;


	
	
	private final UserRepo userRepository;
	
	@Autowired
	IUserManagement userService;




	@Async
	@Scheduled(cron="0 0 0 * * ?")
	public void fixedRateMethod() {
		confirmationTokenRepository.deleteAllTokens();
	}


	
	
	}
	
	

