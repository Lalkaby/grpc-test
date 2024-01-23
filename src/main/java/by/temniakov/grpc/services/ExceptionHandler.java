package by.temniakov.grpc.services;

import by.temnaikov.grpc.model.HelloReply;
import com.google.protobuf.Any;
import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.Metadata;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ExceptionHandler {

    @GrpcExceptionHandler(IllegalArgumentException.class)
    public StatusRuntimeException handleException(IllegalArgumentException error){

        Status status = Status
                .newBuilder()
                .setCode(Code.INVALID_ARGUMENT_VALUE)
                .setMessage("Invalid arguments"+ error.getMessage())
                .addDetails(Any.pack(HelloReply.newBuilder().setMessage(error.getMessage()).build()))
                .build();

        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("some_key",Metadata.ASCII_STRING_MARSHALLER), "some data");

        return StatusProto.toStatusRuntimeException(status, metadata);
    }
}
