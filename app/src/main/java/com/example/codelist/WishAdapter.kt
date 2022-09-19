package com.example.codelist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WishAdapter(private val list: List<Wish>) : RecyclerView.Adapter<WishAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Create member variables for any view that will be set as you render a row.
        val item_name: TextView
        val item_price: TextView
        val item_site: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // Store each of the layout's views into the public final member variables created above
            item_name = itemView.findViewById(R.id.wish_name)
            item_price = itemView.findViewById(R.id.wish_price)
            item_site = itemView.findViewById(R.id.wish_site)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wish_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val listItem = list.get(position)
        // Set item views based on views and data model
        holder.item_name.text = listItem.name
        holder.item_price.text = listItem.value
        holder.item_site.text = listItem.site

        holder.item_site.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(holder.item_site.text.toString()))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + holder.item_name.text.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}