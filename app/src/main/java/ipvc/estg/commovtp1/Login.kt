package ipvc.estg.commovtp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import ipvc.estg.commovtp.API.Endpoints
import ipvc.estg.commovtp.API.ServiceBuilder
import ipvc.estg.commovtp.API.User
import ipvc.estg.commovtp1.API.OutputPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val request = ServiceBuilder.buildService(Endpoints::class.java)


        val usernameText = findViewById<TextView>(R.id.username)

        val passwordText = findViewById<TextView>(R.id.password)
        val teste = findViewById<TextView>(R.id.teste)


        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener {

            val request = ServiceBuilder.buildService(Endpoints::class.java)

            val user = usernameText.text.toString()
            val pass = passwordText.text.toString()

            teste.text=user

            if(user == "") {
                Toast.makeText(
                        applicationContext,
                        getString(R.string.campoutilizador),
                        Toast.LENGTH_LONG).show()
            }
            else if(pass == "") {
                Toast.makeText(
                        applicationContext,
                        getString(R.string.campopassword),
                        Toast.LENGTH_LONG).show()
            }
            else {
                val call = request.login(user, pass)

                call.enqueue(object : Callback<OutputPost> {
                    override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>) {
                        if (response.isSuccessful) {

                            val c: OutputPost = response.body()!!
                            Toast.makeText(this@Login,c.MSG,Toast.LENGTH_SHORT).show()
                            markerInicio(c.id, user)
                        }
                    }

                    override fun onFailure(call: Call<OutputPost>, t: Throwable) {
                        Toast.makeText(this@Login, "${t.message}", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }
    }
    fun markerInicio(marker: String,username:String) {
        val intent = Intent(this, Mapa::class.java)
        intent.putExtra("id_user", marker)
        intent.putExtra("username", username)
        startActivity(intent)
        finish()
    }
}