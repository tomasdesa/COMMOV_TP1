package ipvc.estg.commovtp.API


import android.icu.util.Output
import ipvc.estg.commovtp1.API.OutputPost
import ipvc.estg.commovtp1.API.Outputmarker
import ipvc.estg.commovtp1.API.marker
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    @GET("/myslim/API/marker")
    fun  getMarkers(): Call<List<marker>>


    @GET("/myslim/API/markerUser/{id_user}")
    fun getMarkerByIdUser(@Path("id_user") id:Int): Call<List<marker>>

    @GET("/myslim/API/marker/{id}")
    fun getMarkerById(@Path("id") id:Int?): Call<marker>



    @POST("/myslim/API/markerDelete/{id}")
    fun DeleteMarker(@Path("id") id:Int?): Call<Outputmarker>

    @Multipart
    @POST("/myslim/API/postMarker")
    fun postMarker(@Part("titulo") titulo: RequestBody,
                   @Part("descricao") descricao: RequestBody,
                   @Part("latitude") latitude: RequestBody,
                   @Part("longitude") longitude: RequestBody,
                   @Part imagem: MultipartBody.Part,
                   @Part("tipoproblema") tipoproblema: RequestBody,
                   @Part("id_user") id_user: Int?): Call<Outputmarker>

    @FormUrlEncoded
    @POST("/myslim/API/markerPut/{id}")
    fun updateMarker(@Path("id") id:Int,
                     @Field("titulo") titulo:String?,
                     @Field("descricao") descricao:String?,
                     @Field("tipoproblema") tipoproblema:String?):Call<Outputmarker>
}