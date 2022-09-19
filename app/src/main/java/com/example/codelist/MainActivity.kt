package com.example.codelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var list: List<Wish>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lookup the RecyclerView in activity layout
        val wishRV: RecyclerView = findViewById(R.id.wishRV)
        // Fetch the list of items
        val wishListObject = WishList()
        list = wishListObject.getWishList()
        // Create adapter passing in the list of emails
        val wishAdapter = WishAdapter(list)
        // Attach the adapter to the RecyclerView to populate items
        wishRV.adapter = wishAdapter
        // Set layout manager to position the items
        wishRV.layoutManager = LinearLayoutManager(this)

        // Add item to the list of items inside the WishList object
        val itemName: EditText = findViewById(R.id.name_input)
        val itemPrice: EditText = findViewById(R.id.price_input)
        val itemSite: EditText = findViewById(R.id.site_input)
        val button: Button = findViewById(R.id.button_input)
        button.setOnClickListener {
            wishListObject.addItem(itemName.text.toString(), itemPrice.text.toString(), itemSite.text.toString())
            itemName.text.clear()
            itemPrice.text.clear()
            itemSite.text.clear()
            wishAdapter.notifyDataSetChanged()
        }
    }
}

class WishList{
    var wishes: MutableList<Wish> = mutableListOf<Wish>()

    //Get Items
    fun getWishList(): List<Wish>{
        return wishes
    }

    //Add Item
    fun addItem(name: String, price: String, website: String){
        wishes.add(Wish(name, price, website))
    }
}

