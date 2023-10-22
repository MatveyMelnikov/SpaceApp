package com.example.spaceapp.presantation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceapp.R
import com.example.spaceapp.domain.model.AsteroidInformation
import java.time.format.DateTimeFormatter

class AsteroidRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<AsteroidRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.name.text = "Name:\n${item.name}"
        holder.diameterMax.text = "Max diameter:\n${item.diameterMax} km"
        holder.isPotentiallyHazardous.text =
            "Is potentially hazardous:\n${item.isPotentiallyHazardous}"
        holder.isPotentiallyHazardous.setTextColor(
            ContextCompat.getColor(
                context,
                if (item.isPotentiallyHazardous) R.color.danger else R.color.blizzard
            )
        )
        holder.relativeVelocity.text = "Relative velocity:\n${item.relativeVelocity} km/h"
        holder.missDistance.text = "Miss distance:\n${item.missDistance} km"
        val formatter = DateTimeFormatter.ofPattern("dd LLL yyyy HH:mm")
        holder.closeApproachDate.text = "Close approach date:" +
                "\n${item.closeApproachDate.format(formatter)}"
    }

    override fun getItemCount(): Int = data.size

    private val diffCallback = object : DiffUtil.ItemCallback<AsteroidInformation>() {
        override fun areItemsTheSame(
            oldItem: AsteroidInformation,
            newItem: AsteroidInformation
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: AsteroidInformation,
            newItem: AsteroidInformation
        ): Boolean {
            return oldItem.equals(newItem)
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var data: List<AsteroidInformation>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val diameterMax: TextView = view.findViewById(R.id.diameterMax)
        val isPotentiallyHazardous: TextView = view.findViewById(R.id.isPotentiallyHazardous)
        val relativeVelocity: TextView = view.findViewById(R.id.relativeVelocity)
        val missDistance: TextView = view.findViewById(R.id.missDistance)
        val closeApproachDate: TextView = view.findViewById(R.id.closeApproachDate)
    }
}