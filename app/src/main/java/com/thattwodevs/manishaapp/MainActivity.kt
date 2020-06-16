package com.thattwodevs.manishaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thattwodevs.manishaapp.model.Menu
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val totalAmount = intent.getStringExtra("AMOUNT")
        val menu: ArrayList<Menu> = intent.getSerializableExtra("DATA") as ArrayList<Menu>
        total_cost_textview.text = "$" + totalAmount


        menu.removeAll {
            it.count == 0
        }
        if (menu.size <= 2) {
            showmore.visibility = View.GONE
            val adapter = RecycleAdapter(menu, true, this)
            recyclerView.adapter = adapter
        } else {
            showmore.visibility = View.VISIBLE

            val shortMenu = ArrayList<Menu>()
            shortMenu.add(menu.get(0))
            shortMenu.add(menu.get(1))
            btn_back_mycart.setOnClickListener { finish() }

            val adapter = RecycleAdapter(shortMenu!!, true, this)
            recyclerView.adapter = adapter
        }
        showmore.setOnClickListener {
            showmore.visibility = View.INVISIBLE

            val adapter = RecycleAdapter(menu, true, this)
            recyclerView.adapter = adapter
        }
    }

    public fun getMyTotalQuantity(menu: ArrayList<Menu>) {
        var totalQuantity = 0
        var totalAmount = 0
        for (item in menu.indices) {
            totalQuantity = totalQuantity + menu.get(item).count
            totalAmount = totalAmount + (menu!!.get(item).count * menu!!.get(item).price)
            total_cost_textview.text = "$" + totalAmount.toString()

        }
        if (totalQuantity == 0) {
            finish()
        } else {

        }
    }
}
