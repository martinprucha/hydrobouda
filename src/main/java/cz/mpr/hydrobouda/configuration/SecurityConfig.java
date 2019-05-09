package cz.mpr.hydrobouda.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@SuppressWarnings("deprecation")
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// add our users for in memory authentication
		UserBuilder users = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("password").roles("USER"))
			.withUser(users.username("susan").password("password").roles("USER", "ADMIN"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/home").hasRole("USER")
			.antMatchers("/filestorage").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}

}
