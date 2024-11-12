package com.example.puasasunnah

import Jadwal
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.puasasunnah.databinding.ActivityMainBinding
import com.example.puasasunnah.model.ApiResponse
import com.example.puasasunnah.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayMonth = listOf(
            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        )
        val monthAdapter = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_spinner_item,
            arrayMonth
        )
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMonth.adapter = monthAdapter

        binding.spinnerMonth.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val selectedMonth = position + 1
                    getJadwalList(selectedMonth) { jadwalList ->
                        val adapterJadwal = JadwalAdapter(jadwalList) { jadwal ->
                            Toast.makeText(
                                this@MainActivity,
                                "Tanggal ${jadwal.date} adalah ${jadwal.type}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        binding.rvJadwal.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = adapterJadwal
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
    }

    private fun getJadwalList(month: Int, callback: (List<Jadwal>) -> Unit) {
        val client = ApiClient.getInstance()
        val responseApi = client.getApiResponse(month)

        responseApi.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                val jadwalList = mutableListOf<Jadwal>()
                if (response.isSuccessful && response.body() != null) {
                    for (item in response.body()!!.data) {
                        jadwalList.add(
                            Jadwal(
                                type = item.type.name,
                                date = item.date,
                                humanDate = item.humanDate
                            )
                        )
                    }
                }
                callback(jadwalList)
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Koneksi error: ${t.message}", Toast.LENGTH_LONG).show()
                callback(emptyList())
            }
        })
    }
}
