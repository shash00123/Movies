package android.example.firstapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class WrapperError(statusCode:Long,message:String) : RuntimeException() {
    @Expose
    @SerializedName("status_code")
    private var statusCode: Long? = null

    @Expose
    @SerializedName("message")
    override var message: String? = null
    init {
        this.statusCode=statusCode
        this.message=message
    }


}