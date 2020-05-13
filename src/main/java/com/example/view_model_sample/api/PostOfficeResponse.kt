package com.example.view_model_sample.api

class PostOfficeResponse : ArrayList<PostOfficeDTO>()

data class PostOfficeDTO(
    val Message: String,
    val PostOffice: List<PostOffice>,
    val Status: String
)

data class PostOffice (
    val Block: String,
    val BranchType: String,
    val Circle: String,
    val Country: String,
    val DeliveryStatus: String,
    val Description: Any,
    val District: String,
    val Division: String,
    val Name: String,
    val Pincode: String,
    val Region: String,
    val State: String
)