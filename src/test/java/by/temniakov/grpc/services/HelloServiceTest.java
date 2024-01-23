package by.temniakov.grpc.services;

import by.temnaikov.grpc.model.HelloReply;
import by.temnaikov.grpc.model.HelloRequest;
import by.temniakov.grpc.GrpcApplicationTests;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith({SpringExtension.class})
@BootstrapWith(SpringBootTestContextBootstrapper.class)
class HelloServiceTest  {
    @Autowired
    private HelloServiceImpl helloService;

    @Test
    void testSayHello() throws Exception {
        HelloRequest request = HelloRequest.newBuilder()
                .setName("Test")
                .build();
        StreamRecorder<HelloReply> responseObserver = StreamRecorder.create();
        helloService.sayHello(request, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<HelloReply> results = responseObserver.getValues();
        assertEquals(1, results.size());
        HelloReply response = results.get(0);
        assertEquals(HelloReply.newBuilder()
                .setMessage("Hello ==> Test")
                .build(), response);
    }
}