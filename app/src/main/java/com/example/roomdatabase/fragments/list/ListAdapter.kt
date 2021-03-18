package com.example.roomdatabase.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.Model.User
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<com.example.roomdatabase.fragments.list.ListAdapter.MyViewHolder>(){

    private var userList = emptyList<User>()

    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val  currentitem = userList[position]
        holder.itemView.id_txt.text = currentitem.id.toString()
        holder.itemView.fname_txt.text = currentitem.firstName
        holder.itemView.lname_txt.text = currentitem.lastName
        holder.itemView.age_txt.text = currentitem.age.toString()

        holder.itemView.rowLayout.setOnClickListener {
           val action = listFragmentDirections.actionListFragmentToUpdateFragment(currentitem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }


}
