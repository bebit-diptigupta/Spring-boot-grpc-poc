package com.example.server.service

import com.example.UserIdentifyGrpcKt
import com.example.UserIdentifyRequest
import com.example.UserIdentifyResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service

@Service
class UserIdentifyService: UserIdentifyGrpcKt.UserIdentifyCoroutineImplBase() {

    override suspend fun identifyUser(request: UserIdentifyRequest): UserIdentifyResponse = coroutineScope {
        launch {
            delay(100)
            println("test in launch")
        }

        val newServicePk = async {
            delay(100)
            "new-service-pk"
        }

        UserIdentifyResponse.newBuilder()
            .setUserCase("${newServicePk.await()}_${request.servicePk}_${request.platformPk}")
            .build();
    }


}