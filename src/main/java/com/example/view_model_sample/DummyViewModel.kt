package com.example.view_model_sample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.view_model_sample.api.PostOfficeAPIService
import com.example.view_model_sample.api.PostOfficeResponse

class DummyViewModel(application: Application) : AndroidViewModel(application) {

  private var postOfficeResponse: MutableLiveData<PostOfficeResponse>? = null

  fun postOfficeAPI() : MutableLiveData<PostOfficeResponse>? {
    return if (postOfficeResponse == null) {
      PostOfficeAPIService().hitPostOfficeAPI()
    } else postOfficeResponse
  }

}