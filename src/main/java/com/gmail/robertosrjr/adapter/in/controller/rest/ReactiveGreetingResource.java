package com.gmail.robertosrjr.adapter.in.controller.rest;

import com.gmail.robertosrjr.application.grpc.proto.HelloGrpc;
import com.gmail.robertosrjr.application.grpc.proto.HelloRequest;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class ReactiveGreetingResource {

    @GrpcClient("hello")
    private HelloGrpc hello;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy Reactive";
    }

    @GET
    @Path("/{name}")
    public Uni<String> hello(@PathParam("name") String name) {
        return hello.sayHello(HelloRequest.newBuilder().setName(name).build())
                .onItem().transform(helloReply -> helloReply.getMessage());
    }
}