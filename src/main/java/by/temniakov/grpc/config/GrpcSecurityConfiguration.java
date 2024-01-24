package by.temniakov.grpc.config;

import net.devh.boot.grpc.server.security.check.AccessPredicateVoter;
import net.devh.boot.grpc.server.security.check.GrpcSecurityMetadataSource;
import net.devh.boot.grpc.server.security.check.ManualGrpcSecurityMetadataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true,prePostEnabled = true)
public class GrpcSecurityConfiguration {

//    @Bean
//    GrpcSecurityMetadataSource grpcSecurityMetadataSource() {
//        final ManualGrpcSecurityMetadataSource source = new ManualGrpcSecurityMetadataSource();
//        source.set(MyServiceGrpc.getMethodA(), AccessPredicate.authenticated());
//        source.set(MyServiceGrpc.getMethodB(), AccessPredicate.hasRole("ROLE_USER"));
//        source.set(MyServiceGrpc.getMethodC(), AccessPredicate.hasAllRole("ROLE_FOO", "ROLE_BAR"));
//        source.set(MyServiceGrpc.getMethodD(), (auth, call) -> "admin".equals(auth.getName()));
//        source.setDefault(AccessPredicate.denyAll());
//        return source;
//    }
//
//    @Bean
//    AccessDecisionManager accessDecisionManager() {
//        final List<AccessDecisionVoter<?>> voters = new ArrayList<>();
//        voters.add(new AccessPredicateVoter());
//        return new UnanimousBased(voters);
//    }
}
