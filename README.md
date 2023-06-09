# Spring-boot-grpc-poc
This is a POC for using gRPC with Spring-boot Java project

# proto-common
This service has protobuf files which has gRPC service definition.
It generates the code in Java and Kotlin which is used in client and server services.


# grpc-server
This is an example of Java gRPC server which is developed using [LogNet grpc-spring-boot-starter framework ](https://github.com/LogNet/grpc-spring-boot-starter).
This library will work with Kotlin as well.

# grpc-kotlin-lognet-server
This is an example of Kotlin gRPC server which is developed using [LogNet grpc-spring-boot-starter framework ](https://github.com/LogNet/grpc-spring-boot-starter).

# grpc-kotlin-server
This is an example of Kotlin gRPC server which is developed using [Line Armeria](https://armeria.dev/docs/server-grpc).
**Note:** It might not be as per standard. We need to invest more time to find out how to use Armeria.

# grpc-client
This is an example of Java gRPC client which is developed using below two framework
- [grpc-java](https://github.com/grpc/grpc-java)
- [Line Armeria](https://armeria.dev/docs/server-grpc)
