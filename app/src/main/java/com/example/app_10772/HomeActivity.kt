package com.example.app_10772

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        fetchDataFromServer();
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