package android.example.firstapp

import com.google.gson.annotations.SerializedName


class WrapperResponse<T> {
    @SerializedName("data")
    var data: T? = null
        private set

    @SerializedName("error")
    var error: Boolean? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("status")
    var status: String? = null

    @SerializedName("authToken")
    var authToken: String? = null

    fun setData(data: T) {
        this.data = data
    }
}