package com.example.client.service;

import com.example.UserIdentifyGrpc;
import com.example.UserIdentifyRequest;
import com.example.UserIdentifyResponse;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessorService {

  private final UserIdentifyGrpc.UserIdentifyBlockingStub userIdentifyBlockingStub;

  private final UserIdentifyGrpc.UserIdentifyFutureStub userIdentifyFutureStubViaArmeriaClient;

  public void checkData() {

    UserIdentifyRequest request = UserIdentifyRequest.newBuilder()
        .setServicePk("servicePk")
        .setPlatformPk("platformPk").build();
    // Check found details about status code: https://grpc.github.io/grpc/core/md_doc_statuscodes.html
    //var response = userIdentifyStub
      //  .withDeadline(Deadline.after(1, TimeUnit.SECONDS))
       // .identifyUser(request);

    UserIdentifyResponse response = userIdentifyBlockingStub.identifyUser(request);

    System.out.println(response.toString());

    ListenableFuture<UserIdentifyResponse> futureResponse = userIdentifyFutureStubViaArmeriaClient.identifyUser(request);

    try {
      System.out.println(futureResponse.get());
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
