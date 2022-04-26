package qqq.qqq.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import qqq.qqq.databinding.AdapterViewBinding

class MyRecycleAdapter : RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder>() {

    private lateinit var context: Context
    private var arrayList: ArrayList<String> = arrayListOf()

    class MyViewHolder(val binding: AdapterViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val binding = AdapterViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemData = arrayList[position]
        println(position)

        with(holder.binding) {
            tvName.text = itemData
            btnClick.setOnClickListener {
                Toast.makeText(context, itemData, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int = arrayList.size

    fun setData(arrayData: ArrayList<String>) {
        this.arrayList = arrayData
    }

    fun getData() = this.arrayList
}