package com.example.nyumbakumi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase

class CustomAdapter(var context: Context, var data:ArrayList<House>):BaseAdapter() {
    private class ViewHolder(row:View?){
        var mHouseNumber:TextView
        var mHouseSize:TextView
        var mHousePrice:TextView
        var mimgHousePic:ImageView
        var mbtnDelete:Button
        var btnupdate:Button
        init {
            this.mHouseNumber = row?.findViewById(R.id.mHouseNumber) as TextView
            this.mHouseSize = row?.findViewById(R.id.mHouseSize) as TextView
            this.mHousePrice = row?.findViewById(R.id.mHousePrice) as TextView
            this.mimgHousePic = row?.findViewById(R.id.mimgHousePic) as ImageView
            this.mbtnDelete = row?.findViewById(R.id.mbtnDelete) as Button
            this.btnupdate = row?.findViewById(R.id.btnupdate) as Button
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.house_layout,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var item:House = getItem(position) as House
        viewHolder.mHouseNumber.text = item.houseNumber
        viewHolder.mHouseSize.text = item.houseSize
        viewHolder.mHousePrice.text = item.housePrice
        Glide.with(context).load(item.houseImage).into(viewHolder.mimgHousePic)
        viewHolder.mbtnDelete.setOnClickListener {
            var delref = FirebaseDatabase.getInstance()
                .getReference().child("Houses/"+item.houseID)
            delref.removeValue()
        }
        viewHolder.btnupdate.setOnClickListener {

        }
        return view as View
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }
}