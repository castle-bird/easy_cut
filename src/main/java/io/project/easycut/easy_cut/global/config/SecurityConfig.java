package io.project.easycut.easy_cut.global.config;


import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Value("${app.cors.allowed-origins}")
  private List<String> allowedOrigins;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        // CSRF 비활성화
        .csrf(AbstractHttpConfigurer::disable)

        // CORS 설정
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))

        // form로그인 비활성화
        .formLogin(AbstractHttpConfigurer::disable)

        // 세션 관리 정책을 Stateless로 설정 (JWT 필수 ⭐)
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )

        // 경로별 권한 설정
        .authorizeHttpRequests(auth -> auth
            // 정적 리소스 허용 (CSS, JS, 이미지 등)
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

            // 로그인, 회원가입은 허용
            .requestMatchers(
                "/",
                "/index.html",
                "/favicon.ico",
                "/api/auth/login",
                "/api/auth/signup"
            ).permitAll()

            // 나머지는 인증 필요
            .anyRequest().authenticated()
        );

    // JWT 필터 추가 (UsernamePasswordAuthenticationFilter 이전에 실행)
    // .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    // 허용할 Origin 설정
    configuration.setAllowedOriginPatterns(allowedOrigins);
    
    // 허용할 HTTP Method 설정
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
    
    // 허용할 Header 설정
    configuration.setAllowedHeaders(List.of("*"));
    
    // 자격 증명(Cookie 등) 허용 설정
    configuration.setAllowCredentials(true);
    
    // 브라우저가 Preflight 요청 결과를 캐싱할 시간
    configuration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
