//package com.example.taylanwhite.meh.model
//
//import android.content.ContentValues
//import android.database.Cursor
//import com.dtp.simplemvp.addAll
//import com.dtp.simplemvp.database.item_builder.ItemBuilder
//import com.dtp.simplemvp.database.table.Column
//import com.dtp.simplemvp.database.table.DataTable
//import com.dtp.simplemvp.get
//import com.google.gson.annotations.Expose
//import com.google.gson.annotations.SerializedName
//
//open class Answer(var id: String? = null, var text: String? = null, var voteCount: Int = 0): DataTable {
//
//    companion object {
//        val TABLE_NAME = "Answer"
//
//        val ID = Column(Column.STRING, "Id", notNull = true, unique = true)
//        val TEXT = Column(Column.STRING, "Text")
//        val VOTE_COUNT = Column(Column.INT, "VoteCount")
//
//        val COLUMNS = arrayOf(ID, TEXT, VOTE_COUNT)
//
//        val BUILDER = Builder()
//    }
//
//    override fun contentValues(): ContentValues {
//        val contentValues = ContentValues()
//
//        return ContentValues().addAll(COLUMNS, arrayOf(id, text, voteCount))
//    }
//
//    override fun tableName(): String {
//        return TABLE_NAME
//    }
//
//    class Builder: ItemBuilder<Answer> {
//        override fun buildItem(cursor: Cursor): Answer {
//            return Answer(cursor.get(ID), cursor.get(TEXT), cursor.get(VOTE_COUNT))
//        }
//    }
//}
