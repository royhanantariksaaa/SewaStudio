package com.example.sewastudio.model

import com.google.gson.annotations.SerializedName


class Studio {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("attributes")
    var attributes: StudioAttributes = StudioAttributes()
}

class StudioAttributes{
    @SerializedName("name")
    var name: String = ""
    @SerializedName("ownerID")
    var ownerID: ApiResponse<User> = ApiResponse<User>()
    @SerializedName("createdAt")
    var createdAt: String = ""
    @SerializedName("updatedAt")
    var updatedAt: String = ""
    @SerializedName("publishedAt")
    var publishedAt: String = ""
    @SerializedName("available")
    var available: Any? = null
}
