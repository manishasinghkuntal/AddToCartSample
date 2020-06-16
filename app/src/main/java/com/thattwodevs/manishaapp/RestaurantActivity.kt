package com.thattwodevs.manishaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thattwodevs.manishaapp.model.Menu
import kotlinx.android.synthetic.main.activity_restaurant.*

class RestaurantActivity : AppCompatActivity() {
    var cart: TextView? = null
    var totalItems = 0
    var adapter: RecycleAdapter? = null
    var menu: ArrayList<Menu>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        cart = findViewById(R.id.cart) as TextView

        scroll_view.smoothScrollTo(0, 0)
        recyclerView2.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView2.setFocusable(false)
        recyclerView2.setNestedScrollingEnabled(false)
        menu = ArrayList<Menu>()

        menu!!.add(Menu("Pizza", "Description for pizza", 20, 0))
        menu!!.add(Menu("Burger", "Description for Burger", 100, 0))
        menu!!.add(Menu("Pizza", "Description for pizza", 20, 0))
        menu!!.add(Menu("Pizza", "Description for pizza", 20, 0))
        menu!!.add(Menu("Burger", "Description for Burger", 100, 0))


        adapter = RecycleAdapter(menu!!, false, this)

        recyclerView2.adapter = adapter
        var totalAmount = 0
        cart!!.setOnClickListener {
            for (item in menu!!.indices) {
                totalAmount = totalAmount + (menu!!.get(item).count * menu!!.get(item).price)
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("AMOUNT", totalAmount.toString())
            intent.putExtra("DATA", menu)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        for (item in menu!!.indices) {
            menu!!.get(item).count = 0
        }
        cart!!.visibility = View.GONE
        adapter!!.notifyDataSetChanged()
    }

    public fun getMyTotalQuantity(menu: ArrayList<Menu>) {
        var totalQuantity = 0
        for (item in menu.indices) {

            totalQuantity = totalQuantity + menu.get(item).count
        }
        if (totalQuantity == 0) {
            cart!!.visibility = View.GONE
        } else {
            totalItems = 0
            cart!!.visibility = View.VISIBLE

        }
        cart!!.text = "VIEW CART(" + totalQuantity.toString() + " ITEMS)"
    }
}
