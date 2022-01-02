package agenda.vacina.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementacaoUserDetailsService detailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf()
		.disable() //Desativa as configurações padrões de memória do Spring
		.authorizeRequests()// Permitir restringir acessos
		.antMatchers(HttpMethod.GET, "/").permitAll() //Qualquer usuário pode acessar
		.antMatchers(HttpMethod.GET, "/cadastroConta").permitAll()
		.antMatchers(HttpMethod.POST, "/cadastrarUsuario").permitAll()
		.anyRequest().authenticated().and().formLogin().permitAll()//Permite qualquer usuario 
		.loginPage("/login")
		.defaultSuccessUrl("/Agendamentos")
		.failureUrl("/login?error=true")
		.and().logout()	// Mapeia a URL de sair do sistema e invalida o usuario autenticado
		.logoutSuccessUrl("/")
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService).
		passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		 web.ignoring().antMatchers("/static/**");
	}
}
