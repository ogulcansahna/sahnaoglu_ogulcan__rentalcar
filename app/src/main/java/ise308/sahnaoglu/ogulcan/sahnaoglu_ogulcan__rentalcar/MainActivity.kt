package ise308.sahnaoglu.ogulcan.sahnaoglu_ogulcan__rentalcar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.items.*
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import kotlin.collections.mutableListOf as mutableListOf

class MainActivity : AppCompatActivity() {
    val db by lazy { DBHelper(this) }
    private lateinit var carinfoListAdapter: CarinfoListAdapter
        override fun onCreate(savedInstanceState: Bundle?) { //oncreate
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     //   val editbutton = findViewById<Button>(R.id.edit)
        carinfoListAdapter = CarinfoListAdapter(mutableListOf())
        itemslist.adapter = carinfoListAdapter
        itemslist.layoutManager = LinearLayoutManager(this)
        showData(db.readData())
        val addnewclick = findViewById<Button>(R.id.button)
        val reset = findViewById<Button>(R.id.buttonreset)
          buttonreset.setOnClickListener {
        db.deleteData()
         val intent = Intent(this,MainActivity::class.java)
         startActivity(intent)}

        addnewclick.setOnClickListener {

            val intent = Intent(this,addnew::class.java)
            startActivity(intent)

        }


        }

        fun showData (list:MutableList<EmpModelClass>) {
            var txtBrand = ""
            var txtName = ""
            var txtFuel = ""
            var txtFav = false
            var txtid = 0//db patlar

            list.forEach{
                txtid = it.id
                txtBrand = it.brand
                txtName = it.name
                txtFuel = it.fuel
                txtFav = it.fav
                carinfoListAdapter.addCarData(EmpModelClass(txtid,txtBrand,txtName,txtFuel,txtFav))
            }
        }


}//son










