package com.example.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class GrpcKotlinServerApplication

fun main(args: Array<String>) {
	runApplication<GrpcKotlinServerApplication>(*args)
}
