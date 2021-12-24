package com.apriega77.httprequestusingktor.data.remote

import com.apriega77.httprequestusingktor.data.dto.PostRequest
import com.apriega77.httprequestusingktor.data.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PostService {
    suspend fun getPosts() : List<PostResponse>

    suspend fun createRequest(postRequest : PostRequest) : PostResponse?

    companion object{
        fun create() : PostService {
            return PostServiceImpl(
                client = HttpClient(Android){
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }

            )
        }
    }

}