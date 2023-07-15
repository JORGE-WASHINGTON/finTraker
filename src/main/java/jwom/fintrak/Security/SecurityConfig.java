package jwom.fintrak.Security;

import jwom.fintrak.Auth.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    //private final UserDetailsServiceImpl userDetailsService;
   // private final PasswordEncoder encoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/auth/register")
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                .permitAll()
                .anyRequest()
                .authenticated()
                        .and()
                                .sessionManagement()
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                                .and()
                                                        .authenticationProvider(authenticationProvider)
                                                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        http.headers().frameOptions().disable();


        return http.build();
    }









}
