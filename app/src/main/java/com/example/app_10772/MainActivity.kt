package com.example.app_10772

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var et_username = findViewById(R.id.et_username) as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_reset   = findViewById(R.id.btn_reset) as Button
        var btn_login   = findViewById(R.id.btn_login) as Button

        btn_reset.setOnClickListener{
            et_username.setText("")
            et_password.setText("")
        }

        btn_login.setOnClickListener{
            val username = et_username.text.toString()
            val password = et_password.text.toString()

            if(username.equals(""))
                Toast.makeText(this@MainActivity,"Username tidak boleh kosong!",Toast.LENGTH_LONG).show()
            else if(password.equals(""))
                Toast.makeText(this@MainActivity,"Password tidak boleh kosong!",Toast.LENGTH_LONG).show()
            else{
                performLogin(username.toString(),password.toString())
            }
            performLogin(username, password)
        }
    }
    private fun performLogin(username: String, password: String) {
        val url = "http://192.168.0.47/10772/public/api/user/login"

        val params = JSONObject()
        params.put("username", username)
        params.put("password", password)

        val requestQueue = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(
            Request.Method.POST, url, params,
            Response.Listener { response ->
                // Handle successful login response
                // Example: Start the main activity
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                Toast.makeText(this,"Login berhasil " + username + " ! ", Toast.LENGTH_LONG).show()
                startActivity(intent)
            },
            Response.ErrorListener { error ->
                // Handle error cases
                Toast.makeText(this, "Login failed: "+params, Toast.LENGTH_SHORT).show()
            }
        )
        // Add the request to the RequestQueue
        requestQueue.add(request)
    }
    fun fetchDataFromServer() {
        val url = "http://192.168.0.47:8000/api/member"

        val requestQueue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                // Tangani respons dari server di sini
                val data = response // Respons dari server (misalnya dalam format JSON)

                // Tampilkan data di Toast
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
            },
            { error ->
                // Tangani kesalahan permintaan di sini
                Toast.makeText(this, "Error: " + error.message, Toast.LENGTH_SHORT).show()
            })

        requestQueue.add(stringRequest)
    }
}