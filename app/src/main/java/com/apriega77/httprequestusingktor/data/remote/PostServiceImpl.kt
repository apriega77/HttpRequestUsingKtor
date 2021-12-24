package com.apriega77.httprequestusingktor.data.remote

import com.apriega77.httprequestusingktor.data.dto.PostRequest
import com.apriega77.httprequestusingktor.data.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.lang.Exception

class PostServiceImpl( private val client : HttpClient) : PostService {



    override suspend fun getPosts(): List<PostResponse> {
       return try{
           client.get {
               url(HttpRoutes.POSTS)
           }
       }catch (e: RedirectResponseException) {
           // 3xx response
           print("Error: ${e.response.status.description}")
           emptyList()
       }catch (e: ClientRequestException) {
           // 4xx response
           print("Error: ${e.response.status.description}")
           emptyList()
       } catch (e: ServerResponseException) {
           // 5xx response
           print("Error: ${e.response.status.description}")
           emptyList()
       }catch (e: Exception) {
           // 6xx response
           print("Error: ${e.message}")
           emptyList()
       }
    }

    override suspend fun createRequest(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse>{
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        }catch (e: RedirectResponseException) {
            // 3xx response
            print("Error: ${e.response.status.description}")
            null
        }catch (e: ClientRequestException) {
            // 4xx response
            print("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx response
            print("Error: ${e.response.status.description}")
            null
        }catch (e: Exception) {
            // 6xx response
            print("Error: ${e.message}")
            null
        }
    }
}