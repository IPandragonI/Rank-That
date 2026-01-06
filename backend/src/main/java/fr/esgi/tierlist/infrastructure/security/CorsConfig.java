package fr.esgi.tierlist.infrastructure.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class CorsConfig implements CorsConfigurationSource {
    @Override
    @SuppressWarnings("NullableProblems")
    public org.springframework.web.cors.CorsConfiguration getCorsConfiguration(
            jakarta.servlet.http.HttpServletRequest request
    ) {
        org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();

        configuration.setAllowedOrigins(java.util.Arrays.asList(
                "http://localhost:3000",
                "http://localhost:5173"
        ));

        configuration.setAllowedMethods(java.util.Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
        ));

        configuration.setAllowedHeaders(java.util.Arrays.asList(
                "Authorization",
                "Content-Type",
                "X-Requested-With",
                "Accept",
                "Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers"
        ));

        configuration.setExposedHeaders(java.util.Arrays.asList(
                "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials",
                "Authorization"
        ));

        configuration.setAllowCredentials(true);

        configuration.setMaxAge(3600L);

        return configuration;
    }
}
