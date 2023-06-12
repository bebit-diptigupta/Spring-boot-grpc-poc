package com.example.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrpcKotlinServerApplication

fun main(args: Array<String>) {
	runApplication<GrpcKotlinServerApplication>(*args)
}