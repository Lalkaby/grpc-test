package by.temniakov.grpc.model.mapper;

import by.temnaikov.grpc.model.CustomUser;
import by.temnaikov.grpc.model.CustomUserInfo;
import by.temniakov.grpc.model.CustomUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CustomUserMapper {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomUserEntity mapFromUserInfo(CustomUserInfo customUserInfo){
        CustomUserEntity user = new CustomUserEntity();
        user.setUsername(customUserInfo.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(customUserInfo.getPassword()));
        user.setEmail(customUserInfo.getEmail());
        user.setRoles(customUserInfo.getRolesList().toArray(new String[0]));

        return user;
    }

    public CustomUser mapFromUser(CustomUserEntity customUser){
        return CustomUser.newBuilder()
                        .setId(customUser.getId().toString())
                        .setUsername(customUser.getUsername())
                        .setEmail(customUser.getEmail())
                        .addAllRoles(Arrays.stream(customUser.getRoles()).toList())
                        .build();
    }

    public CustomUserEntity mapFromNameAndPass(String username, String password){
        CustomUserEntity user = new CustomUserEntity();
        user.setUsername(username);
        return user;
    }

    public boolean passwordMatches(CharSequence rawPassword, String hashedPassword){

        return bCryptPasswordEncoder.matches(rawPassword,hashedPassword);
    }
}
