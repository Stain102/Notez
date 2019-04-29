package com.stain.Notez

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class NoteActivity : AppCompatActivity() {

    companion object {
        val IS_NEW = "is_new"
        val TITLE = "title"
        val TEXT = "text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        supportActionBar?.title = if (intent.getBooleanExtra(IS_NEW, true)) "New Note" else "Edit Note"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.action_save) {
            // ToDo: save / add note to collections of notes
            Toast.makeText(this, "Note was saved!", Toast.LENGTH_SHORT).show()
            finish()
            return true
        } else super.onOptionsItemSelected(item)
    }
}
