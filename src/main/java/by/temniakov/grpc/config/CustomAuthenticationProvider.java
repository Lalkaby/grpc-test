package by.temniakov.grpc.config;

import by.temniakov.grpc.model.CustomUserEntity;
import by.temniakov.grpc.services.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    CustomUserServiceImpl customUserService;

    @Override
    public Authentication authenticate(Authentication authentication){
        CustomUserEntity user = customUserService.tryAuthUser(authentication);
        var result =  new UsernamePasswordAuthenticationToken(user.getUsername(), null,
                Arrays.stream(user.getRoles())
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
