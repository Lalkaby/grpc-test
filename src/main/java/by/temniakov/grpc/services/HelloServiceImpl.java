package by.temniakov.grpc.services;

import by.temnaikov.grpc.model.HelloReply;
import by.temnaikov.grpc.model.HelloRequest;
import by.temnaikov.grpc.services.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloServiceImpl  extends HelloServiceGrpc.HelloServiceImplBase{

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello ==> " + request.getName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}