package com.example.puasasunnah

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.puasasunnah.databinding.ActivityMainBinding
import com.example.puasasunnah.model.ApiResponse
import com.example.puasasunnah.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getJadwalList(1)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding){
            val arrayMonth = listOf("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
            val monthAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, arrayMonth)
            monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerMonth.adapter = monthAdapter

            spinnerMonth.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        getJadwalList(p2 + 1)
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }
                }
        }
    }

    fun getJadwalList(month: Int){
        val client = ApiClient.getInstance()
        val responseApi = client.getApiResponse(month)
        val jadwalList = ArrayList<String>()

        responseApi.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(p0: Call<ApiResponse>, p1: Response<ApiResponse>) {
                for(i in p1.body()!!.data){
                    jadwalList.add(i.humanDate)
                }
                val listAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, jadwalList)
                binding.lvJadwal.adapter = listAdapter
            }
            override fun onFailure(p0: Call<ApiResponse>, p1: Throwable) {
                Toast.makeText(this@MainActivity, "koneksi error", Toast.LENGTH_LONG).show()
            }
        })
    }
}