package ise308.sahnaoglu.ogulcan.sahnaoglu_ogulcan__rentalcar

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items.view.*


class CarinfoListAdapter (
    private val Carinfolist : MutableList<EmpModelClass>
):RecyclerView.Adapter<CarinfoListAdapter.CarViewHolder>(){

    class CarViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.items,parent,false
            )
        )
    }
    fun addCarData(CarData: EmpModelClass){
        Carinfolist.add(CarData)
        notifyItemInserted(Carinfolist.size - 1)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val CurrentPosition = Carinfolist[position]

        holder.itemView.apply {

            brand.text = CurrentPosition.brand
            name.text = CurrentPosition.name
            fuel.text = CurrentPosition.fuel
            if (CurrentPosition.fav == false){
                fuel.setBackgroundColor(Color.LTGRAY)
                name.setBackgroundColor(Color.LTGRAY)
                brand.setBackgroundColor(Color.LTGRAY)
            }
            else{
                fuel.setBackgroundColor(Color.GRAY)
                name.setBackgroundColor(Color.GRAY)
                brand.setBackgroundColor(Color.GRAY)
            }

        }
    }



    override fun getItemCount(): Int {

        return Carinfolist.size
    }

}



