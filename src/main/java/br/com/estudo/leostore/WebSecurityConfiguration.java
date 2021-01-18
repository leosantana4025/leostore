package br.com.estudo.leostore;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/usuario/pedido", true)
				.permitAll()
		).logout(logout -> logout.logoutUrl("/logout"))
		.csrf().disable();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		//UserDetails userDetails = User.builder().username("leonardo").password(bCryptPasswordEncoder.encode("123")).roles("ADM").build();
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(bCryptPasswordEncoder);
		//.withUser(userDetails);
		
	}
	
}
