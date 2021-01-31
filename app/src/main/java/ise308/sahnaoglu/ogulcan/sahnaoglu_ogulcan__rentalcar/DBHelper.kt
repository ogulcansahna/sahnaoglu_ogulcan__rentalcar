package ise308.sahnaoglu.ogulcan.sahnaoglu_ogulcan__rentalcar

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBHelper (val context: Context) :
SQLiteOpenHelper(context, DBHelper.DATABASE_NAME,null,DBHelper.DATABASE_VERSION){

    private val TABLE_NAME="Carlist"
    private val CAR_ID = "id"
    private val CAR_BRAND = "brand"
    private val CAR_NAME = "name"
    private val CAR_FUEL = "fuel"
    private val CAR_FAV = "fav"

    companion object{
        private val DATABASE_NAME = "SQLITE_DATABASE"
        private val DATABASE_VERSION = 1
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($CAR_ID INTEGER PRIMARY KEY AUTOINCREMENT, $CAR_BRAND VARCHAR(256) NOT NULL , $CAR_NAME VARCHAR(256) NOT NULL PRIMARY KEY, $CAR_FUEL VARCHAR(256) NOT NULL, $CAR_FAV BOOLEAN)"
        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(cars: EmpModelClass){
        val sqliteDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(CAR_BRAND , cars.brand)
        contentValues.put(CAR_NAME, cars.name)
        contentValues.put(CAR_FUEL, cars.fuel)
        contentValues.put(CAR_FAV, cars.fav)


        val result = sqliteDB.insert(TABLE_NAME,null,contentValues)

        Toast.makeText(context,if(result != -1L) "Kayıt Başarılı" else "Kayıt yapılamadı.", Toast.LENGTH_SHORT).show()
    }

    fun readData():MutableList<EmpModelClass>{
        val userList = mutableListOf<EmpModelClass>()
        val sqliteDB = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = sqliteDB.rawQuery(query,null)
        var valuefav = 0
        if(result.moveToFirst()){
            do {
                val cars = EmpModelClass()
                cars.id = result.getString(result.getColumnIndex(CAR_ID)).toInt()
                cars.brand = result.getString(result.getColumnIndex(CAR_BRAND))
                cars.name = result.getString(result.getColumnIndex(CAR_NAME))
                cars.fuel = result.getString(result.getColumnIndex(CAR_FUEL))
                valuefav = result.getString(result.getColumnIndex(CAR_FAV)).toInt()
                if (valuefav == 0){
                    cars.fav = false
                }
                else{
                    cars.fav = true
                }
                userList.add(cars)
            }while (result.moveToNext())
        }
        result.close()
        sqliteDB.close()
        return userList
    }

    fun updateCar(fav:Boolean, name: String ) {
        val db = this.writableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                val cv = ContentValues()
                cv.put(CAR_BRAND,(result.getString(result.getColumnIndex(CAR_NAME))+name))
                cv.put(CAR_BRAND,(result.getString(result.getColumnIndex(CAR_FAV))+fav))
                db.update(TABLE_NAME,cv, "$CAR_ID=? AND $CAR_BRAND=?",
                        arrayOf(result.getString(result.getColumnIndex(CAR_ID)),
                                result.getString(result.getColumnIndex(CAR_BRAND))))
            }while (result.moveToNext())
        }
        result.close()
        db.close()
    }
    fun deleteData(){
        val sqliteDB = this.writableDatabase
        sqliteDB.delete(TABLE_NAME,null,null)
        sqliteDB.close()
//"$CAR_ID=? AND $CAR_BRAND=?"
    }

    fun listOfCarinfo(): ArrayList<EmpModelClass>{
        val db = this.writableDatabase
        val res = db.rawQuery("select 0 from " + TABLE_NAME, null)
        val useList = ArrayList<EmpModelClass>()
        while (res.moveToNext()){
            var carinfo = EmpModelClass ()
            carinfo.id = Integer.valueOf(res.getString(0))
            carinfo.brand = res.getString(1)
            carinfo.name = res.getString(2)
            carinfo.fuel = res.getString(3)
            useList.add(carinfo)
        }
        return useList
    }


}