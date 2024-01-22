package by.temniakov.grpc.services;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ExceptionHandler {

    @GrpcExceptionHandler(IllegalArgumentException.class)
    public StatusRuntimeException handleException(IllegalArgumentException error){

        return Status.CANCELLED
                .withDescription("abracadaabra "+ error.getMessage())
                .asRuntimeException();
    }
}
