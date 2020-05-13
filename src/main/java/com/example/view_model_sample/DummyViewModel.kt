package com.example.view_model_sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.view_model_sample.api.PostOfficeAPIService
import com.example.view_model_sample.api.PostOfficeResponse

class DummyViewModel : ViewModel() {

  internal var postOfficeResponse: MutableLiveData<PostOfficeResponse> = MutableLiveData<PostOfficeResponse>()
//    get() = savedStateHandle.getLiveData(NAME_KEY)
  // so it can be observed for changes.
  // getLiveData obtains an object that is associated with the key wrapped in a LiveData

  fun postOfficeAPI() {
    postOfficeResponse = PostOfficeAPIService().hitPostOfficeAPI()
  }

//  private fun savePostOffice(postOfficeResponse: LiveData<PostOfficeResponse>) {
//    // Sets a new value for the object associated to the key. There's no need to set it
//    // as a LiveData.
//    savedStateHandle.set(NAME_KEY, postOfficeResponse.value)
//  }

  companion object {
    private const val NAME_KEY = "PostOffice"
  }
}
