package com.thattwodevs.manishaapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.thattwodevs.manishaapp.model.Menu


class RecycleAdapter(val userList: ArrayList<Menu>, val isCart: Boolean, val context: Context) :
    RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.my_cart_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecycleAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position], isCart, context, userList)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var minus: TextView? = null
        var plus: TextView? = null
        var num: TextView? = null
        var add: TextView? = null
        var countLayout: LinearLayout? = null

        fun bindItems(menu: Menu, isCart: Boolean, context: Context, userList: ArrayList<Menu>) {
            val item_name = itemView.findViewById(R.id.item_name) as TextView
            val item_desc = itemView.findViewById(R.id.item_desc) as TextView
            val item_cost = itemView.findViewById(R.id.item_cost) as TextView
            minus = itemView.findViewById(R.id.minus) as TextView
            plus = itemView.findViewById(R.id.plus) as TextView
            num = itemView.findViewById(R.id.num) as TextView
            add = itemView.findViewById(R.id.add) as TextView
            countLayout = itemView.findViewById(R.id.count_layout) as LinearLayout

            val chatImg = itemView.findViewById(R.id.chat_image) as ImageView
            if (isCart == true) {
                chatImg.visibility = View.VISIBLE
                if(menu.count>0){
                    num!!.text=menu.count.toString()
                    add!!.visibility = View.GONE
                    countLayout!!.visibility = View.VISIBLE
                }
                else{
                    countLayout!!.visibility = View.GONE
                    add!!.visibility = View.VISIBLE

                }
            } else chatImg.visibility = View.GONE
            item_name.text = menu.name
            item_desc.text = menu.desc
            item_cost.text ="$"+ menu.price.toString()
            if(menu.count>0){
                num!!.text=menu.count.toString()
                add!!.visibility = View.GONE
                countLayout!!.visibility = View.VISIBLE
            }
            else{
                countLayout!!.visibility = View.GONE
                add!!.visibility = View.VISIBLE

            }
            countsManagement(menu, context, userList,isCart)
        }

        fun countsManagement(menu: Menu, context: Context, userList: ArrayList<Menu>, isCart: Boolean) {
            var finalnum = menu.count
            var sum = 0
            sum = sum + menu.count
            num!!.text = finalnum.toString()
            minus!!.setOnClickListener {
                if (finalnum > 0) {
                    finalnum = finalnum - 1

                    if (finalnum == 0) {
                        add!!.visibility = View.VISIBLE
                        countLayout!!.visibility = View.GONE
                        menu.count = finalnum
                        if(isCart==false) {
                            (context as RestaurantActivity).getMyTotalQuantity(userList)
                        }
                        else{
                            (context as MainActivity).getMyTotalQuantity(userList)
                        }

                    } else {
                        num!!.text = finalnum.toString()
                        add!!.visibility = View.GONE
                        countLayout!!.visibility = View.VISIBLE
                        menu.count = finalnum
                        if(isCart==false) {
                            (context as RestaurantActivity).getMyTotalQuantity(userList)
                        }
                        else{
                            (context as MainActivity).getMyTotalQuantity(userList)
                        }

                    }
                }
                else {
                    menu.count = 0
                    add!!.visibility = View.VISIBLE
                    countLayout!!.visibility = View.GONE
                }
            }
            plus!!.setOnClickListener {

                finalnum = menu.count + 1
                num!!.text = finalnum.toString()
                sum = 0
                sum = sum + finalnum
                add!!.visibility = View.GONE
                countLayout!!.visibility = View.VISIBLE
                menu.count = finalnum
                if(isCart==false) {
                    (context as RestaurantActivity).getMyTotalQuantity(userList)
                }
                else{
                    (context as MainActivity).getMyTotalQuantity(userList)
                }
            }
            add!!.setOnClickListener {
                add!!.visibility = View.GONE
                countLayout!!.visibility = View.VISIBLE
                menu.count = 1
                num!!.text = menu.count.toString()

                if(isCart==false) {
                    (context as RestaurantActivity).getMyTotalQuantity(userList)
                }
                else{
                    (context as MainActivity).getMyTotalQuantity(userList)
                }

            }
        }


    }
}

