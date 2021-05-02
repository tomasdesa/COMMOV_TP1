package ipvc.estg.commovtp.API


import ipvc.estg.commovtp1.API.OutputPost
import ipvc.estg.commovtp1.API.Outputmarker
import ipvc.estg.commovtp1.API.marker
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

    @FormUrlEncoded
    @POST("/myslim/API/postMarker")
    fun postMarker( @Field("titulo") titulo:String?,
                    @Field("descricao") descricao:String?,
                    @Field("longitude") longitude:Double?,
                    @Field("latitude") latitude:Double?,
                    @Field("imagem") imagem:String?,
                    @Field("tipoproblema") tipoproblema:String?,
                    @Field("id_user") id_user: Int?): Call<Outputmarker>
}