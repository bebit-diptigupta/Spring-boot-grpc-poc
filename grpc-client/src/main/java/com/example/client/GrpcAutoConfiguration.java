package com.example.client;


import com.example.UserIdentifyGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ClientSettings.class)
public class GrpcAutoConfiguration {

  @Bean
  ManagedChannel managedChannel(ClientSettings settings) {
    return NettyChannelBuilder.forAddress(settings.getAddress(), settings.getPort())
        .usePlaintext() // when not using TLS
        .build();
  }

  @Bean
  UserIdentifyGrpc.UserIdentifyBlockingStub userIdentifyBlockingStub(ManagedChannel managedChannel) {
    return UserIdentifyGrpc.newBlockingStub(managedChannel);
  }

  @Bean
  UserIdentifyGrpc.UserIdentifyFutureStub userIdentifyFutureStub(ManagedChannel managedChannel) {
    return UserIdentifyGrpc.newFutureStub(managedChannel);
  }
}


