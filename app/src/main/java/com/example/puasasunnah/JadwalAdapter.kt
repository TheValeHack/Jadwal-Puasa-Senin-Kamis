package com.example.puasasunnah

import Jadwal
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.puasasunnah.databinding.ItemPuasaBinding

typealias OnClickJadwal = (Jadwal) -> Unit

class JadwalAdapter(private val listJadwal: List<Jadwal>, private val onClickJadwal: OnClickJadwal): RecyclerView.Adapter<JadwalAdapter.ItemJadwalViewHolder>() {
    inner class ItemJadwalViewHolder(private val binding: ItemPuasaBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Jadwal){
            with(binding){
                txtJadwal.setText(data.humanDate)
                jadwalItem.setOnClickListener{
                    onClickJadwal(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemJadwalViewHolder {
        val binding = ItemPuasaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemJadwalViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listJadwal.size
    }

    override fun onBindViewHolder(holder: ItemJadwalViewHolder, position: Int) {
        holder.bind(listJadwal[position])
    }
}