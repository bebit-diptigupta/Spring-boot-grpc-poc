package com.example.client;

import com.example.client.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {

    SpringApplication.run(Application.class, args);

  }

}

@Component
class CommandLineAppStartupRunner implements CommandLineRunner {
  @Autowired
  private ProcessorService processorService;

  @Override
  public void run(String...args) throws Exception {
    processorService.checkData();

  }
}