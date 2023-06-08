package com.example.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "client-setting")
@Getter
@Setter
public class ClientSettings {

  private String address;
  private int port;

}