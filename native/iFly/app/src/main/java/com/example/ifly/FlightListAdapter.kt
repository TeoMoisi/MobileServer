package com.example.ifly

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ifly.model.Flight

class FlightListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<FlightListAdapter.FlightViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var flights = emptyList<Flight>() // Cached copy of words
    //private var connection: Boolean = true


    inner class FlightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idView: TextView = itemView.findViewById(R.id.id_text)
        val dateTime: TextView = itemView.findViewById(R.id.date_time)
        val departure: TextView = itemView.findViewById(R.id.departure)
        val arrival: TextView = itemView.findViewById(R.id.arrival)
    }

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Flight
            val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id.toString()).
                putExtra("DEPARTURE", item.departure).putExtra("ARRIVAL", item.arrival).putExtra("CODE", item.code)
                    .putExtra("DATETIME", item.datetime)
            }
            //v.context.startActivity(intent)
            (context as Activity).startActivityForResult(intent, 4)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val itemView = inflater.inflate(R.layout.item_list_content, parent, false)
        return FlightViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val current = flights.get(position)
        holder.idView.text = current.id.toString()
        holder.dateTime.text = current.datetime
        holder.departure.text = current.departure
        holder.arrival.text = current.arrival

        with(holder.itemView) {
            tag = current
            setOnClickListener(onClickListener)
        }
    }

    internal fun setFlights(flights: List<Flight>) {
        this.flights = flights
        notifyDataSetChanged()
    }

    override fun getItemCount() = flights.size
}