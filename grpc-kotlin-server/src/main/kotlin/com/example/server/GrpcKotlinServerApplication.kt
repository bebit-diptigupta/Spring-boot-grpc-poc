package com.example.server

import com.example.UserIdentifyGrpcKt
import com.example.server.service.UserIdentifyService
import com.linecorp.armeria.common.grpc.GrpcSerializationFormats
import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.docs.DocServiceFilter
import com.linecorp.armeria.server.grpc.GrpcService
import io.grpc.protobuf.services.ProtoReflectionService
import io.grpc.reflection.v1alpha.ServerReflectionGrpc
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrpcKotlinServerApplication


fun main(args: Array<String>) {
	runApplication<GrpcKotlinServerApplication>(*args)
	val server = newServer(8000)

	server.closeOnJvmShutdown()

	server.start().join()
}

fun newServer(httpPort: Int, useBlockingTaskExecutor: Boolean = false): Server {
	val grpcService = GrpcService.builder()
		// See https://github.com/grpc/grpc-java/blob/master/documentation/server-reflection-tutorial.md
		.addService(UserIdentifyService())
		.addService(ProtoReflectionService.newInstance())
		.supportedSerializationFormats(GrpcSerializationFormats.values())
		.enableUnframedRequests(true)
		// You can set useBlockingTaskExecutor(true) in order to execute all gRPC
		// methods in the blockingTaskExecutor thread pool.
		.useBlockingTaskExecutor(useBlockingTaskExecutor)
		.build()
	return Server.builder()
		.http(httpPort)
		.service(grpcService) // You can access the documentation service at http://127.0.0.1:8080/docs.
		// See https://armeria.dev/docs/server-docservice for more information.
		.serviceUnder(
			"/docs",
			DocService.builder()
				.exampleRequests(
					UserIdentifyGrpcKt.SERVICE_NAME,
					"userIdentify"
				)
				.exclude(
					DocServiceFilter.ofServiceName(
						ServerReflectionGrpc.SERVICE_NAME
					)
				)
				.build()
		)
		.build()
}