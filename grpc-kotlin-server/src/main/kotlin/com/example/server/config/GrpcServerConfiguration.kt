package com.example.server.config

import com.linecorp.armeria.common.grpc.GrpcSerializationFormats
import com.linecorp.armeria.common.logging.LogLevel
import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.grpc.GrpcService
import com.linecorp.armeria.server.logging.LoggingService
import com.linecorp.armeria.spring.ArmeriaServerConfigurator
import io.grpc.BindableService
import io.grpc.ServerInterceptors
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class GrpcServerConfiguration {

    @Bean
    fun armeriaServerConfigurator(
        grpcServices: List<BindableService>
    ) = ArmeriaServerConfigurator { serverBuilder ->
        serverBuilder
            .service(
                getGrpcService(grpcServices),
                LoggingService.builder()  // logging decorator; by default no logging
                    .requestLogLevel(LogLevel.INFO)
                    .successfulResponseLogLevel(LogLevel.INFO)
                    .failureResponseLogLevel(LogLevel.WARN)
                    .newDecorator()
            )
            .serviceUnder("/docs", DocService())
    }

    private fun getGrpcService(grpcServices: List<BindableService>): GrpcService =
        GrpcService.builder()
            .addServiceDefinitions(
                grpcServices.map {
                    ServerInterceptors.intercept(it)
                }
            )
            // by default, supports all.
            .supportedSerializationFormats(
                GrpcSerializationFormats.PROTO,
                GrpcSerializationFormats.JSON // Note: it's needed to use debug form in document service.
            )
            // For https://armeria.dev/docs/server-docservice/
            // Note: It's needed to debug the service via document service
            .enableUnframedRequests(true)
            .build()

}