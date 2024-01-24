package by.temniakov.grpc.services;

import by.temnaikov.grpc.model.CustomUser;
import by.temnaikov.grpc.model.CustomUserInfo;
import by.temnaikov.grpc.services.CustomUserServiceGrpc;
import by.temniakov.grpc.model.CustomUserEntity;
import by.temniakov.grpc.model.mapper.CustomUserMapper;
import by.temniakov.grpc.repository.CustomUserRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@GrpcService
public class CustomUserServiceImpl extends CustomUserServiceGrpc.CustomUserServiceImplBase {
    private final CustomUserRepository customUserRepository;
    private final CustomUserMapper customUserMapper;

    @Override
    @Transactional
    public void registerUser(CustomUserInfo customUserInfo, StreamObserver<CustomUser> responseObserver) {
        var userInfo = customUserMapper.mapFromUserInfo(customUserInfo);
        if (customUserRepository.existsByUsername(userInfo.getUsername())){
            throw new IllegalArgumentException("User with such username already exists.");
        }
        var user = customUserRepository.save(userInfo);

        responseObserver.onNext(customUserMapper.mapFromUser(user));
        responseObserver.onCompleted();
    }

    public CustomUserEntity tryAuthUser(Authentication authentication) {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        var user = customUserRepository
                .findUserForAuthOrElseThrow(Example.of(customUserMapper.mapFromNameAndPass(name,password)));
        if (!customUserMapper.passwordMatches(password,user.getPassword())){
            throw new AuthenticationCredentialsNotFoundException("User not found or password is wrong.");
        }

        return user;
    }
}
