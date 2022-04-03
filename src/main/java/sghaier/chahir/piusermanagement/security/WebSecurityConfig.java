package sghaier.chahir.piusermanagement.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import sghaier.chahir.piusermanagement.User.UserService;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));
		http.csrf().disable();
    	http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/SpringMVC/login/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/user/users/**").hasAnyAuthority("User");
		http.authorizeRequests().antMatchers("/user/**").hasAnyAuthority("ADMIN");
		http.addFilter(new CustomAuthentificationFilter(authenticationManagerBean()));
		http.addFilterBefore(new CustomAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
		http
        .authorizeRequests()
            .antMatchers("/registration/**","/face/**","/RendezVous/**","/pdf/**")
            .permitAll()
            
        .anyRequest()
        
        .authenticated();

		
		
		
		
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
