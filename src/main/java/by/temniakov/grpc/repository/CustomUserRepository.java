package by.temniakov.grpc.repository;

import by.temniakov.grpc.model.CustomUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomUserRepository extends JpaRepository<CustomUserEntity, UUID> {
}
