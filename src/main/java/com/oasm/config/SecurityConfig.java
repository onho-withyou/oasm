package com.oasm.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oasm.domain.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                                .requestMatchers("/font/**","/img/**","/login","/").permitAll()
                                .requestMatchers("/**").hasAuthority(UserRole.A01.name())
                                .requestMatchers("/base/**").hasAuthority(UserRole.B01.name())
                                .requestMatchers("/auth/**").hasAuthority(UserRole.middle.name())
                                .requestMatchers("/setUsers/**").hasAuthority(UserRole.TEST.name())
                                .anyRequest().authenticated()
                )
                .headers(
                        headersConfigurer ->
                                headersConfigurer
                                        .frameOptions(
                                                HeadersConfigurer.FrameOptionsConfig::sameOrigin
                                        )
                )
                .formLogin((formLogin) -> formLogin
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true))
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling.accessDeniedPage("/"))
        ;
        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 리소스 spring security 대상에서 제외
        return (web) ->
                web
                        .ignoring()
                        .requestMatchers(
                                PathRequest.toStaticResources().atCommonLocations()
                        );
    }
    private final AuthenticationEntryPoint unauthorizedEntryPoint =
            (request, response, authException) -> {
                ErrorResponse fail = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Spring security unauthorized...");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };
    private final AccessDeniedHandler accessDeniedHandler =
            (request, response, accessDeniedException) -> {
                ErrorResponse fail = new ErrorResponse(HttpStatus.FORBIDDEN, "Spring security forbidden...");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };
    @Getter
    @RequiredArgsConstructor
    public class ErrorResponse {
        private final HttpStatus status;
        private final String message;
    }
    // 암호화
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    //스프링 시큐리티의 인증을 담당, 사용자 인증시 앞에서 작성한 UserSecurityService 와 PasswordEncoder 를 사용
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
