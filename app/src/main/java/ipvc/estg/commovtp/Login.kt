package ipvc.estg.commovtp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import ipvc.estg.commovtp.API.Endpoints
import ipvc.estg.commovtp.API.ServiceBuilder
import ipvc.estg.commovtp.API.User

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

                    val i = response.body()!!
                    var a = i.size

                    val login = findViewById<Button>(R.id.login)
                    login.setOnClickListener {
                        var num: Int = 0
                        var aux: Int = 0
                        val user = usernameText.text.toString()
                        val pass = passwordText.text.toString()
                        teste.text=user
                        do {
                            if (i[num].username == user) {
                                teste.text = "fds"
                                aux = 1

                                if (i[num].pasword == pass) {

                                    teste.text= "login"

                                }else{
                                    Toast.makeText(
                                            applicationContext,
                                            getString(R.string.passErrada),
                                            Toast.LENGTH_SHORT).show()
                                }
                            }
                            num++;
                        } while (num < a)

                        if(aux == 0) {
                            Toast.makeText(
                                    applicationContext,
                                    getString(R.string.naoexiste),
                                    Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@Login, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}