package com.sp.spring.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Configuration
@AllArgsConstructor
public class AuthorizationServerConfig {

    // http://localhost:3001/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=https://springone.io/authorized&code_challenge=QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8&code_challenge_method=S256
    // http://localhost:3001/oauth2/token?client_id=client&redirect_uri=https://springone.io/authorized&grant_type=authorization_code&code=dWlJMGpGlUAPz0sRU1y8suXDyWejo0_B4-WrLP-ks5kSlcdvlGG-u1OxOORvvpm7IMJaC_lMqzTX2Oh6AKHGOb2J4-Hp6PVPvGjLeUQMnWzz6h3Xyy1D0S6czbiTeU8f&code_verifier=qPsH306-ZDDaOE8DFzVn05TkN3ZZoVmI_6x4LsVglQI
    private final CORSCustomizer corsCustomizer;

    @Bean
    @Order(1)
    public SecurityFilterChain asSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        corsCustomizer.corsCustomizer(http);

        //http.cors((Customizer<CorsConfigurer<HttpSecurity>>) corsCustomizer);

        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                //Validation for authorizationEndpoint
                .authorizationEndpoint(
                        a -> a.authenticationProviders(getAuthorizationEndpointProviders())
                )
                //Validation for token endpoint
                /*.tokenEndpoint(
                        a -> a.accessTokenRequestConverters()
                )*/
                .oidc(Customizer.withDefaults());

        http.exceptionHandling(
                e -> e.authenticationEntryPoint(
                        new LoginUrlAuthenticationEntryPoint("/login")
                )
        );

        return http.build();
    }

    private Consumer<List<AuthenticationProvider>> getAuthorizationEndpointProviders() {
        return providers -> {
            for (AuthenticationProvider p : providers) {
                if (p instanceof OAuth2AuthorizationCodeRequestAuthenticationProvider x) {
                    x.setAuthenticationValidator(new CustomRedirectUriValidator());
                }
            }
        };
    }

    @Bean
    @Order(2)
    public SecurityFilterChain appSecurityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeHttpRequests().anyRequest().authenticated();

        return http.build();
    }

    //By creating a AppUserDetailsService class and adding @Service annotation on it, we have created
    //a Bean for UserDetailsService. We can now remove following InMemoryUserDetailsManager bean.
    /*@Bean
    public UserDetailsService userDetailsService() {
        var u1 = User.withUsername("user")
                .password("password")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(u1);
    }*/

    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //TODO move client registration into DB
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("client")
                //.clientSecret("secret")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .redirectUri("https://springone.io/authorized")
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                /* Don't know what this setting is for
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(true).build())*/
                .tokenSettings(
                        TokenSettings.builder()
                                .accessTokenTimeToLive(Duration.ofSeconds(900))
                                .build()
                )
                .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    //Use this Bean if you want to change the URLs exposed by Authorization Server default configuration
    //http://auth-server:3001/.well-known/openid-configuration
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .build();
    }

    //TODO move security keys into DB
    @Bean
    public JWKSource<SecurityContext> jwkSource() throws Exception {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        kg.initialize(2048);
        KeyPair kp = kg.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();

        RSAKey key = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();

        JWKSet set = new JWKSet(key);
        return new ImmutableJWKSet(set);
    }

    //Customize how your token will look like. Below method is used to include authorities into generated token
    @Bean
    OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return context -> {
            if (context.getTokenType() == OAuth2TokenType.ACCESS_TOKEN) {
                Authentication principal = context.getPrincipal();
                Set<String> authorities = principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet());
                context.getClaims().claim("roles", authorities);
            }
        };
    }

}
