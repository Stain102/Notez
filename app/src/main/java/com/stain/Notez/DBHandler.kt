package com.stain.Notez

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.stain.Notez.models.Note

class DBHandler(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private val DB_NAME = "Notes.db"
        private val DB_VERSION = 1

        const val NOTE_TABLE_NAME = "notes"
        const val NOTE_ID = "id"
        const val NOTE_TITLE = "title"
        const val NOTE_TEXT = "text"
        const val NOTE_TIMESTAMP = "timestamp"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_NOTE_TABLE = "CREATE TABLE $NOTE_TABLE_NAME (" +
                "$NOTE_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NOTE_TITLE TEXT," +
                "$NOTE_TEXT TEXT, " +
                "$NOTE_TIMESTAMP TEXT)"

        db?.execSQL(CREATE_NOTE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getAllNotes(): ArrayList<Note> {
        val notes = ArrayList<Note>()
        val db = this.readableDatabase
        val cursor = db.query(NOTE_TABLE_NAME, null, null, null, null, null, null)

        while (cursor.moveToNext()) {
            notes.add(
                    Note(
                            cursor.getInt(cursor.getColumnIndex(NOTE_ID)),
                            cursor.getString(cursor.getColumnIndex(NOTE_TITLE)),
                            cursor.getString(cursor.getColumnIndex(NOTE_TEXT)),
                            cursor.getString(cursor.getColumnIndex(NOTE_TIMESTAMP))
                    )
            )
        }

        cursor.close()
        db.close()
        return notes
    }

    fun addNote(note: Note) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(NOTE_TITLE, note.title)
        cv.put(NOTE_TEXT, note.text)
        cv.put(NOTE_TIMESTAMP, note.timestamp)

        db.insert(NOTE_TABLE_NAME, null, cv)
        db.close()
    }

    fun updateNote(note: Note) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(NOTE_TITLE, note.title)
        cv.put(NOTE_TEXT, note.text)
        cv.put(NOTE_TIMESTAMP, note.timestamp)

        db.update(NOTE_TABLE_NAME, cv, "id=${note.id}", null)
        db.close()
    }

    fun deleteNote(id: Int) {
        val db = this.writableDatabase

        db.delete(NOTE_TABLE_NAME, "id=$id", null)
        db.close()
    }
}