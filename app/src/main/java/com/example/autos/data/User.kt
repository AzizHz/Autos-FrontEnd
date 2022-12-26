package com.example.autos.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    var _id: String ? = null,
    @SerializedName("FullName")
    var FullName: String ? = null,
    @SerializedName("Email")
                var Email: String? = null,
    @SerializedName("Phone")
                var Phone: String? = null,
    @SerializedName("Password")
                var Password: String ? = null,
    @SerializedName("DateOfBirth")
                var DateOfBirth : String ? = null,
    @SerializedName("Country")
                var Country: String  ? = null,
    @SerializedName("State")
                var State: String  ? = null,
    @SerializedName("HomeAddress")
                var HomeAddress: String  ? = null,
    @SerializedName("AccountState")
                var AccountState: String ? = null,

                )


