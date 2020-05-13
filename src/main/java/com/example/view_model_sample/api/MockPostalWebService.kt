package com.example.view_model_sample.api


import retrofit2.Call
import retrofit2.http.*

/**
 * Created by januprasad on 2/4/19.
 */

interface MockPostalWebService {


    @GET("/pincode/{pinCode}")
    fun hitPostOfficeAPI(@Path("pinCode")pinCode:String): Call<PostOfficeResponse>

}
