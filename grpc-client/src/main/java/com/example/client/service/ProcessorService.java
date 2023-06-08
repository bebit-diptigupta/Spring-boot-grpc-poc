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

  public void checkData() {

    UserIdentifyRequest request = UserIdentifyRequest.newBuilder()
        .setServicePk("servicePk")
        .setPlatformPk("platformPk").build();
    // Check found details about status code: https://grpc.github.io/grpc/core/md_doc_statuscodes.html
    //var response = userIdentifyStub
      //  .withDeadline(Deadline.after(1, TimeUnit.SECONDS))
       // .identifyUser(request);

    UserIdentifyResponse response = userIdentifyStub.identifyUser(request);

    System.out.println(response.toString());
  }

}
