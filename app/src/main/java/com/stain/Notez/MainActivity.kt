package com.stain.Notez

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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
        noteAdapter = NoteAdapter(notes, this)
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
            openNoteActivity(true, getNewNote())
            true
        } else super.onOptionsItemSelected(item)
    }

    fun openNoteActivity(isNew: Boolean, note: Note) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra(NoteActivity.IS_NEW, isNew)
        intent.putExtra(NoteActivity.ID, note.id)
        intent.putExtra(NoteActivity.TITLE, note.title)
        intent.putExtra(NoteActivity.TEXT, note.text)
        intent.putExtra(NoteActivity.TIMESTAMP, note.timestamp)

        startActivity(intent)
    }

    fun deleteNote(id: Int) {
        // ToDo: delete note with the specified id!
        Toast.makeText(this, "Note #$id was deleted!", Toast.LENGTH_SHORT).show()
    }

    private fun getNewNote(): Note {
        return Note(getNoteId(), "", "", "Created: ----")
    }

    private fun getNoteId(): Int {
        return id
    }

    // region Temp
    fun addNote(note: Note) {
        notes.add(note)
    }

    /**
     * Temp fun to populate list of notes.
     */
    private fun addNotes() {
        for (i in 0..9) {
            addNote(Note(getNoteId(),"Title #$i", "Sample text #$i", "Updated: ----"))
            id++
        }
    }
    // endregion
}
