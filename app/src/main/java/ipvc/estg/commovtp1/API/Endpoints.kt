package ipvc.estg.commovtp.API


import ipvc.estg.commovtp1.API.OutputPost
import retrofit2.http.*
import retrofit2.http.Path
import retrofit2.Call

interface Endpoints {

    @GET("/myslim/API/user")
    fun  getUsers(): Call<List<User>>

    @GET("/user/{id}")
    fun getUserById(@Path("id") id:Int): Call<User>

    @FormUrlEncoded
    @POST("/myslim/API/user/login")
    fun login(@Field("username") username:String?,
    @Field("password") password:String?): Call<OutputPost>
}