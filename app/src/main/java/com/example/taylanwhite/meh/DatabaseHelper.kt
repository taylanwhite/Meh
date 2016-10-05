package com.example.taylanwhite.meh

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by taylanwhite on 10/4/16.
 */
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "com.example.taylanwhite.meh", null, 1) {



    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL("create table DEALTABLE(VIDEO TEXT, NAME TEXT, PRICE TEXT, DESCRIPTION TEXT, SPECIFICATION TEXT, BUY TEXT, NOTIFICATIONS TEXT, IMAGES TEXT, COLOR TEXT)" )
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DEALTABLE")
        onCreate(sqLiteDatabase)
    }


    fun insert_deal(video:String, name:String, price:String, description:String, specification:String, buy:String, notifications: String, images:String, color:String) {
        var contentValues = ContentValues()
        contentValues.put("VIDEO", video)
        contentValues.put("NAME", name)
        contentValues.put("PRICE", price)
        contentValues.put("DESCRIPTION", description)
        contentValues.put("SPECIFICATION", specification)
        contentValues.put("BUY", buy)
        contentValues.put("NOTIFICATIONS", notifications)
        contentValues.put("IMAGES", images)
        contentValues.put("COLOR", color)
        this.writableDatabase.insertOrThrow("DEALTABLE", "", contentValues)

    }

    fun delete_deal(video:String){

        this.writableDatabase.delete("DEALTABLE", "VIDEO=?", arrayOf(video))
    }

    fun update_deal(oldVideo:String, newVideo:String){
        this.writableDatabase.execSQL("UPDATE DEALTABLE SET VIDEO='$newVideo' WHERE VIDEO='$oldVideo'")
    }

    fun list_all_deals()
    {
        var cursor = this.readableDatabase.query("DEALTABLE", null, null, null, null, null, null)

        while(cursor.moveToNext()) {
            cursor.getString(cursor.getColumnIndex("VIDEO"))
//            listView.append(cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(5) + " " + cursor.getString(6) + " " + cursor.getString(7) + " " + cursor.getString(8)+ " " + cursor.getString(9))
        }

        cursor.close()
    }

}
