/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employee.mangment.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
    
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder){
        String encodedPassword= passwordEncoder.encode("1234");
        return new InMemoryUserDetailsManager(
                
                User.withUsername("user1").password(encodedPassword).roles("USER").build(),
                User.withUsername("user2").password(encodedPassword).roles("USER").build(),
                User.withUsername("admin").password(encodedPassword).roles("USER","ADMIN").build()
        );
    }
    
      @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
        failureHandler.setDefaultFailureUrl("/login?error=true");
        return failureHandler;
    }
    
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	/*http
		.authorizeHttpRequests(authorize -> authorize
			.anyRequest().authenticated()
		)
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());*/
  return httpSecurity

            .csrf(csrf -> csrf.disable()) // Temporarily disable CSRF to rule it out
            .headers(headers -> headers.frameOptions().disable()) // For H2 console
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/error", "/webjars/**", "/h2-console/**", "/notAuthorized").permitAll()
                .requestMatchers("/user/**").hasAnyRole("USER")
                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                .requestMatchers("/delete/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureHandler(authenticationFailureHandler())
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedPage("/login?denied=true") // Redirect to login with denied parameter
                )
            .sessionManagement(session -> session
                .maximumSessions(1)
                .expiredUrl("/login?expired=true") // Handle expired sessions
                )
            .build();
}
}
     