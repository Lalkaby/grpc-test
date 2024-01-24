package by.temniakov.grpc.services;


import by.temnaikov.grpc.model.HelloReply;
import by.temnaikov.grpc.model.HelloRequest;
import by.temnaikov.grpc.services.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

@GrpcService
public class HelloServiceImpl  extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    @PreAuthorize("isAuthenticated()")
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {

        if (request.getName().isEmpty()){
            responseObserver.onError(new IllegalArgumentException("mazafuk"));
            return;
        }

        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello ==> " + request.getName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}