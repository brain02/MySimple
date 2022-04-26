package qqq.qqq.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import java.util.ArrayList


class DiffUtilCallback(
    private val oldList: ArrayList<String>,
    private val newList: ArrayList<String>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldString: String = oldList[oldItemPosition]
        val newString: String = newList[newItemPosition]
        return oldString === newString
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldString: String = oldList[oldItemPosition]
        val newString: String = newList[newItemPosition]
        return (oldString.equals(newString) && oldString === newString)
    }
}