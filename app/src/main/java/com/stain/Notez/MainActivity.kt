package com.stain.Notez

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.stain.Notez.models.Note

class MainActivity : AppCompatActivity() {

    var notes : ArrayList<Note> = ArrayList() // generates new list. Alternative for initializing empty list "emptyList()"
    var noteAdapter : NoteAdapter? = null
    var id: Int = 0
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.note_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // ToDo: remove fun call
        addNotes()

        // ToDo: change to use db data retrieval with callback
        // ToDo: populate notes with data
        noteAdapter = NoteAdapter(notes)
        recyclerView.adapter = noteAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        return if (id == R.id.action_add) {
            //Toast.makeText(this, "Add button clicked!", Toast.LENGTH_SHORT).show()
            openNoteActivity(true)
            true
        } else super.onOptionsItemSelected(item)
    }

    fun openNoteActivity(isNew: Boolean) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra(NoteActivity.IS_NEW, isNew)
        intent.putExtra(NoteActivity.TITLE, "")
        intent.putExtra(NoteActivity.TEXT, "")

        startActivity(intent)
    }

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun getNoteId(): Int {
        val noteId = id
        id++
        return noteId
    }

    /**
     * Temp fun to populate list of notes.
     */
    private fun addNotes() {
        for (i in 1..10) {
            addNote(Note(getNoteId(),"Title #$i", ""))
        }
    }
}
