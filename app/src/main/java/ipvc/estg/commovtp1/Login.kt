package ipvc.estg.commovtp1

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
            val user = usernameText.text.toString()
            val pass = passwordText.text.toString()

            val call = request.login(user, pass)
            teste.text="kekw"

            call.enqueue(object : Callback<OutputPost> {
                override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>) {

                    if (response.isSuccessful) {

                        val i = response.body()!!
                        Toast.makeText(this@Login,i.username,Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<OutputPost>, t: Throwable) {
                    Toast.makeText(this@Login, "${t.message}", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}