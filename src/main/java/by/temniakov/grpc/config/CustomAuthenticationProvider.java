package by.temniakov.grpc.config;

import by.temniakov.grpc.services.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    CustomUserServiceImpl customUserService;

    @Override
    public Authentication authenticate(Authentication authentication){

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (name!="Yan" || password!="Yan"){
            return null;
        }

        return new UsernamePasswordAuthenticationToken(
                name, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
