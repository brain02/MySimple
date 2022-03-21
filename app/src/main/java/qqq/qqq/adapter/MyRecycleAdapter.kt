package qqq.qqq.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import qqq.qqq.R

class MyRecycleAdapter : RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder>() {

    private lateinit var context: Context
    private var arrayList: ArrayList<String> = arrayListOf()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val btnCLick: Button = itemView.findViewById(R.id.btnClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_view, parent, false)
        context = parent.context
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemData = arrayList[position]
        holder.tvName.text = itemData
        holder.btnCLick.setOnClickListener {
            Toast.makeText(context, itemData, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int = arrayList.size

    fun setData(arrayData: ArrayList<String>) {
        this.arrayList = arrayData
    }
}