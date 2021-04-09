package ipvc.estg.commovtp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ipvc.estg.commovtp1.API.Endpoints
import ipvc.estg.commovtp1.API.ServiceBuilder
import ipvc.estg.commovtp1.API.User

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val request = ServiceBuilder.buildService(Endpoints::class.java)
        val call = request.getUsers()


        val usernameText = findViewById<TextView>(R.id.username)

        val passwordText = findViewById<TextView>(R.id.password)
        val teste=findViewById<TextView>(R.id.teste)


        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful){

                    val login = findViewById<Button>(R.id.login)
                   login.setOnClickListener {
                        val username = usernameText.text.toString()
                        val password = passwordText.text.toString()

                        var i = response.body()


                        teste.text= response.body().toString()

                    }

                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@Login, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}