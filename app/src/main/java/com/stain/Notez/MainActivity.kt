package com.stain.Notez

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.stain.Notez.models.Note

class MainActivity : AppCompatActivity(), NoteAdapter.ItemClickListener {

    lateinit var dbHandler: DBHandler
    lateinit var noteAdapter : NoteAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        dbHandler = DBHandler(this)

        recyclerView = findViewById(R.id.note_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        noteAdapter = NoteAdapter(this, dbHandler.getAllNotes())
        noteAdapter.setItemClickListener(this)
        recyclerView.adapter = noteAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.action_add) {
            val intent = Intent(this, NoteActivity::class.java)
            intent.putExtra(NoteActivity.NOTE, Note(-1,"",""))
            startActivityForResult(intent, NoteActivity.REQ_CODE_ADD)
            return true
        } else super.onOptionsItemSelected(item)
    }

    override fun onEditClicked(adapterPos: Int) {
        val note = noteAdapter.getItem(adapterPos)
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra(NoteActivity.IS_NEW, false)
        intent.putExtra(NoteActivity.NOTE, note)

        startActivityForResult(intent, NoteActivity.REQ_CODE_UPDATE)
    }

    override fun onDelClicked(adapterPos: Int) {
        val note = noteAdapter.getItem(adapterPos)
        val deleteNote = {
            dbHandler.deleteNote(note.id)
            noteAdapter.itemRemoved(adapterPos)
        }

        AlertDialog.Builder(this@MainActivity)
                .setTitle("Deleting Note")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes"){_, _ -> deleteNote() }
                .setNegativeButton("No"){_, _ -> }
                .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val note = data?.getParcelableExtra<Note>(NoteActivity.NOTE)

            if (requestCode == NoteActivity.REQ_CODE_ADD) {
                dbHandler.addNote(note!!)
                noteAdapter.itemAdded(note)
            } else if (requestCode == NoteActivity.REQ_CODE_UPDATE) {
                dbHandler.updateNote(note!!)
                noteAdapter.itemChanged(note)
            }
        }
    }
}
