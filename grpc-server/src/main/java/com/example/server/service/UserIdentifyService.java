package com.example.server.service;

import com.example.UserIdentifyGrpc.UserIdentifyImplBase;
import com.example.UserIdentifyRequest;
import com.example.UserIdentifyResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserIdentifyService extends UserIdentifyImplBase {

  @Override
  public void identifyUser(UserIdentifyRequest request,
      StreamObserver<UserIdentifyResponse> responseObserver) {
    UserIdentifyResponse response = UserIdentifyResponse.newBuilder()
        .setUserCase(request.getServicePk() + "-"+request.getPlatformPk())
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
