package com.example.view_model_sample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.view_model_sample.api.PostOfficeAPIService
import com.example.view_model_sample.api.PostOfficeDTO
import com.example.view_model_sample.api.PostOfficeResponse

class DummyViewModel(application: Application) : AndroidViewModel(application) {

  fun postOfficeAPI() = PostOfficeAPIService().hitPostOfficeAPI()
//  fun postOfficeDTO() : MutableLiveData<PostOfficeDTO>{
//    var mutableLiveData = PostOfficeAPIService().hitPostOfficeAPI()
//    return makePostOfficeDTO(mutableLiveData.value)
//  }

  private fun makePostOfficeDTO(postOfficeResponse: PostOfficeResponse?): MutableLiveData<PostOfficeDTO> {
    var postOfficeResponseMutableLiveData = MutableLiveData<PostOfficeDTO>()
//    if(postOfficeResponse==null){
//      postOfficeResponseMutableLiveData.postValue(null)
//    }else{
//      //Parse PostOffice
//      postOfficeResponse[0]
//          .let {
//            postOfficeResponseMutableLiveData.postValue(it)
//          }
//    }
    return postOfficeResponseMutableLiveData
  }


}