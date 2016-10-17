package com.example.taylanwhite.meh.presenter

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import com.dtp.simplemvp.database.table.TableBuilder
import com.example.taylanwhite.meh.model.Deal
import com.example.taylanwhite.meh.model.DealObject
import com.example.taylanwhite.meh.model.Item
import com.example.taylanwhite.meh.model.Video
import java.lang.reflect.Array
import java.util.*

/**
 * Created by taylanwhite on 10/4/16.
 */
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "com.example.taylanwhite.meh", null, 1) {

    companion object {
        //deal object
        val VIDEO = "VIDEO"
        val NAME = "NAME"
        val PRICE = "PRICE"
        val DESCRIPTION = "DESCRIPTION"
        val SPECIFICATION = "SPECIFICATION"
        val BUY = "BUY"
        val COLOR = "COLOR"

        //photo object
        val PHOTO_URL = "PHOTOURL"

    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {


//        sqLiteDatabase.execSQL("create table DEALTABLE(deal_id integer PRIMARY KEY, VIDEO TEXT, photo_id integer NOT NULL, FOREIGN KEY (photo_id) REFERENCES DEALPHOTO(photo_id), NAME TEXT, PRICE TEXT, DESCRIPTION TEXT, SPECIFICATION TEXT, BUY TEXT, COLOR TEXT)" )
//        sqLiteDatabase.execSQL("create table DEALPHOTO(photo_id integer PRIMARY KEY, PHOTO TEXT)") NOT NULL UNIQUE
        sqLiteDatabase.execSQL("create table DEALTABLE(DEAL_ID TEXT NOT NULL UNIQUE, $VIDEO TEXT, $NAME TEXT, $PRICE TEXT, $DESCRIPTION TEXT, $SPECIFICATION TEXT, $BUY TEXT, $COLOR TEXT)" )
        sqLiteDatabase.execSQL("create table DEALPHOTO(DEAL_ID TEXT NOT NULL, $PHOTO_URL TEXT UNIQUE)")


    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DEALTABLE")
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DEALPHOTO")
        onCreate(sqLiteDatabase)
    }


    fun insert_deal(deal_id:String, video:String, name:String, price:String, description:String, specification:String, buy:String,  color:String) {
        var contentValues = ContentValues()
        contentValues.put("DEAL_ID", deal_id)
        contentValues.put(VIDEO, video)
        contentValues.put(NAME, name)
        contentValues.put(PRICE, price)
        contentValues.put(DESCRIPTION, description)
        contentValues.put(SPECIFICATION, specification)
        contentValues.put(BUY, buy)
        contentValues.put(COLOR, color)
        this.writableDatabase.insertWithOnConflict("DEALTABLE", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE)

    }


    fun insert_photo(deal_id:String, photoUrl:String){
        var contentValues = ContentValues()
        contentValues.put("DEAL_ID", deal_id)
        contentValues.put(PHOTO_URL, photoUrl)
        this.writableDatabase.insertWithOnConflict("DEALPHOTO", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE)
    }

    fun delete_deal(video:String, photo:String){

        this.writableDatabase.delete("DEALTABLE", "VIDEO=?", arrayOf(video))
        this.writableDatabase.delete("DEALPHOTO", "PHOTO=?", arrayOf(photo))

    }

    fun update_deal(oldVideo:String, newVideo:String, oldPhoto:String, newPhoto:String){
        this.writableDatabase.execSQL("UPDATE DEALTABLE SET VIDEO='$newVideo' WHERE VIDEO='$oldVideo'")
        this.writableDatabase.execSQL("UPDATE DEALPHOTO SET PRICE='$newPhoto' WHERE PRICE='$oldPhoto'")
    }

//    fun list_certain_deals() : List<Deal>
//    {
//        var deals = ArrayList<Deal>()
//
//        var cursor = this.readableDatabase.query("DEALTABLE", null, null, null, null, null, null)
//    }
    fun get_all_deals() : List<DealObject> {

        var deals = ArrayList<DealObject>()

//        val query = "SELECT * FROM DEALTABLE"
//
//        val cursor = this.readableDatabase.rawQuery(query, null)

        val cursor = this.readableDatabase.query("DEALTABLE", null, null, null, null, null, null)

        while(cursor.moveToNext()) {
            val dealId = cursor.getString(cursor.getColumnIndex("DEAL_ID"))
            val fastURL = cursor.getString(cursor.getColumnIndex("$VIDEO"))



            var tmpDeal = Deal()
            var tmpVideo = Video()
            var photoCursor = this.readableDatabase.query("DEALPHOTO", null, "DEAL_ID=?", arrayOf(dealId), null, null, null)

            val photos = mutableListOf<String>()
            while(photoCursor.moveToNext()) {
                 photos.add(photoCursor.getString(photoCursor.getColumnIndex(PHOTO_URL)))
            }

            tmpDeal.photos = photos


            tmpVideo.url = cursor.getString(cursor.getColumnIndex("$VIDEO"))
            tmpDeal.title = cursor.getString(cursor.getColumnIndex("$NAME"))
            tmpDeal.items = listOf(Item().apply { price = cursor.getInt(cursor.getColumnIndex(PRICE)) })
            tmpDeal.features = cursor.getString(cursor.getColumnIndex("$DESCRIPTION"))
            tmpDeal.topic?.url = cursor.getString(cursor.getColumnIndex("$SPECIFICATION"))
            tmpDeal.url = cursor.getString(cursor.getColumnIndex("$BUY"))
            tmpDeal.theme?.backgroundColor = cursor.getString(cursor.getColumnIndex("$COLOR"))


            deals.add(DealObject(tmpDeal, tmpVideo)  )
            photoCursor.close()

        }
        cursor.close()


        return deals
    }

}
