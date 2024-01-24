package by.temniakov.grpc.repository;

import by.temniakov.grpc.model.CustomUserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import java.util.UUID;

public interface CustomUserRepository extends JpaRepository<CustomUserEntity, UUID> {
    boolean existsByUsername(String username);

    default CustomUserEntity findUserForAuthOrElseThrow(Example<CustomUserEntity> example){
        return findOne(example)
                .orElseThrow(
                        ()-> new AuthenticationCredentialsNotFoundException("User not found or password is wrong.")
                );
    }
}
