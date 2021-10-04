package com.gmail.robertosrjr.adapter.in.controller.grpc;

import com.gmail.robertosrjr.application.grpc.proto.HelloReply;
import com.gmail.robertosrjr.application.grpc.proto.HelloRequest;
import com.gmail.robertosrjr.application.grpc.proto.HelloGrpc;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

@GrpcService
public class HelloGrpcService implements HelloGrpc {

    @Override
    public Uni<HelloReply> sayHello(HelloRequest request) {
        return Uni.createFrom().item("Hello " + request.getName() + "!")
                .map(msg -> HelloReply.newBuilder().setMessage(msg).build());
    }

}
