package com.example.taylanwhite.meh.presenter

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dtp.simplemvp.database.table.TableBuilder
import com.example.taylanwhite.meh.model.Deal
import java.util.*

/**
 * Created by taylanwhite on 10/4/16.
 */
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "com.example.taylanwhite.meh", null, 1) {



    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {


        sqLiteDatabase.execSQL("create table DEALTABLE(deal_id integer PRIMARY KEY, VIDEO TEXT, photo_id integer NOT NULL, FOREIGN KEY (photo_id) REFERENCES DEALPHOTO(photo_id), NAME TEXT, PRICE TEXT, DESCRIPTION TEXT, SPECIFICATION TEXT, BUY TEXT, COLOR TEXT)" )
        sqLiteDatabase.execSQL("create table DEALPHOTO(photo_id integer PRIMARY KEY, PHOTO TEXT)")

    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DEALTABLE")
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DEALPHOTO")
        onCreate(sqLiteDatabase)
    }


    fun insert_deal(video:String, name:String, price:String, description:String, specification:String, buy:String,  color:String) {
        var contentValues = ContentValues()
        contentValues.put("VIDEO", video)
        contentValues.put("NAME", name)
        contentValues.put("PRICE", price)
        contentValues.put("DESCRIPTION", description)
        contentValues.put("SPECIFICATION", specification)
        contentValues.put("BUY", buy)
        contentValues.put("COLOR", color)
        this.writableDatabase.insertOrThrow("DEALTABLE", "", contentValues)

    }


    fun insert_photo(photo:String){
        var contentValues = ContentValues()
        contentValues.put("PHOTO", photo)
        this.writableDatabase.insertOrThrow("DEALPHOTO", "", contentValues)
    }

    fun delete_deal(video:String, photo:String){

        this.writableDatabase.delete("DEALTABLE", "VIDEO=?", arrayOf(video))
        this.writableDatabase.delete("DEALPHOTO", "PHOTO=?", arrayOf(photo))

    }

    fun update_deal(oldVideo:String, newVideo:String, oldPhoto:String, newPhoto:String){
        this.writableDatabase.execSQL("UPDATE DEALTABLE SET VIDEO='$newVideo' WHERE VIDEO='$oldVideo'")
        this.writableDatabase.execSQL("UPDATE DEALPHOTO SET PRICE='$newPhoto' WHERE PRICE='$oldPhoto'")
    }

    fun list_all_deals() : List<Deal> {
        var deals = ArrayList<Deal>()

        var cursor = this.readableDatabase.query("DEALTABLE", null, null, null, null, null, null)

        while(cursor.moveToNext()) {
//            cursor.getString(cursor.getColumnIndex("PRICE"))

//            listView.append(cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(5) + " " + cursor.getString(6) + " " + cursor.getString(7) + " " + cursor.getString(8)+ " " + cursor.getString(9))
        }
        cursor.close()

        return deals
    }

}
