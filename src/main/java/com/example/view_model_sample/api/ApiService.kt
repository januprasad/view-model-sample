package com.example.view_model_sample.api

open class ApiService {

    protected val apiService: MockPostalWebService
        get() {
            return APIServiceBuilder.createAuthService(
                MockPostalWebService::class.java
            )
        }

}
