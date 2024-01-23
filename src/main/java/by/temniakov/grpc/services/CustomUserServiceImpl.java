package by.temniakov.grpc.services;

import by.temnaikov.grpc.model.CustomUser;
import by.temnaikov.grpc.model.CustomUserInfo;
import by.temnaikov.grpc.services.CustomUserServiceGrpc;
import by.temniakov.grpc.model.mapper.CustomUserMapper;
import by.temniakov.grpc.repository.CustomUserRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl extends CustomUserServiceGrpc.CustomUserServiceImplBase {
    private final CustomUserRepository customUserRepository;
    private final CustomUserMapper customUserMapper;

    @Override
    @Transactional
    public void registerUser(CustomUserInfo customUserInfo, StreamObserver<CustomUser> responseObserver) {
        var userInfo = customUserMapper.mapFromUserInfo(customUserInfo);
        var user = customUserRepository.save(userInfo);

        responseObserver.onNext(customUserMapper.mapFromUser(user));
        responseObserver.onCompleted();
    }
}
