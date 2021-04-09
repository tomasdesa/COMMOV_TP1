package ipvc.estg.commovtp1.API


import retrofit2.http.*
import retrofit2.http.Path
import retrofit2.Call

interface Endpoints {

    @GET("/user/")
    fun  getUsers(): Call<List<User>>

    @GET("/user/{id}")
    fun getUserById(@Path("id") id:Int): Call<User>
}