package com.example.client.service;

import com.example.UserIdentifyGrpc;
import com.example.UserIdentifyRequest;
import com.example.UserIdentifyResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class ProcessorService {

  @GrpcClient("user-identify")
  private UserIdentifyGrpc.UserIdentifyBlockingStub userIdentifyStub;

  public void checkData(){

    UserIdentifyRequest request = UserIdentifyRequest.newBuilder()
            .setServiceId("test")
                .setUserId("123").build();
    UserIdentifyResponse response = userIdentifyStub.identifyUser(request);

    System.out.println(response.toString());
  }

}
