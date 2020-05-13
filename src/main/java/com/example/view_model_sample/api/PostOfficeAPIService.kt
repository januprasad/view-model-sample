package com.example.view_model_sample.api

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class PostOfficeAPIService : ApiService() {

    fun hitPostOfficeAPI(): MutableLiveData<PostOfficeResponse> {

        var postOfficeResponseMutableLiveData = MutableLiveData<PostOfficeResponse>()


        apiService.hitPostOfficeAPI("682017").enqueue(object : Callback<PostOfficeResponse> {
            override fun onFailure(call: Call<PostOfficeResponse>, t: Throwable) {
                postOfficeResponseMutableLiveData.postValue(null)
                }

                override fun onResponse(call: Call<PostOfficeResponse>, response: Response<PostOfficeResponse>) {
                    if (response.body() != null) {
                        postOfficeResponseMutableLiveData.postValue(response.body())
                    } else {
                        postOfficeResponseMutableLiveData.postValue(null)
                    }
                }
        })
        return postOfficeResponseMutableLiveData
    }

}