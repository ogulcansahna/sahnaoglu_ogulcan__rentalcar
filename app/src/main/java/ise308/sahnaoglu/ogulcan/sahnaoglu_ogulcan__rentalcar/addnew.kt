package ise308.sahnaoglu.ogulcan.sahnaoglu_ogulcan__rentalcar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class addnew : AppCompatActivity() {
    val db by lazy { DBHelper(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnew)
        val textView_rental = findViewById<EditText>(R.id.rental)
        var rental_value =""
        val textView_brand = findViewById<EditText>(R.id.addbrand)
        var brand_value =""
        val textView_name = findViewById<EditText>(R.id.addname)
        var name_value =""
        val textView_fuel = findViewById<EditText>(R.id.addfuel)
        var fuel_value =""
        var rent = false
        val save = findViewById<Button>(R.id.save)
        textView_brand.addTextChangedListener(){
            brand_value = textView_brand.text.toString()
        }
        textView_rental.addTextChangedListener(){
            rental_value = textView_rental.text.toString()
            if (rental_value.toUpperCase() == "RENT"){
                rent = true
            }
            else if(rental_value.toUpperCase()=="SALE"){
                rent = false
            }

        }
        textView_name.addTextChangedListener(){
            name_value = textView_name.text.toString()
        }
        textView_fuel.addTextChangedListener(){
            fuel_value = textView_fuel.text.toString()
        }

        save.setOnClickListener {
            if(rental_value.toUpperCase() != "RENT"&& rental_value.toUpperCase() != "SALE"){
                Toast.makeText(this,"Enter the 'Rent' or 'Sale'", Toast.LENGTH_LONG).show()
            }
            else if (brand_value == "" || name_value == "" || fuel_value == ""){
                Toast.makeText(this,"Fill Spaces", Toast.LENGTH_LONG).show()

            }
            else{
                db.insertData(EmpModelClass(brand = brand_value,fav = rent,name= name_value ,fuel = fuel_value))

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)


            }

        }

    }
}