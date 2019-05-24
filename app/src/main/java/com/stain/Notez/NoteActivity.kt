package com.stain.Notez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast

class NoteActivity : AppCompatActivity() {

    companion object {
        val IS_NEW = "is_new"
        val ID = "id"
        val TITLE = "title"
        val TEXT = "text"
        val TIMESTAMP = "timestamp"
    }

    private var mId = 0
    private var mIsNew = true
    private var mStoredTitle = ""
    private var mStoredText = ""
    private var title: EditText? = null
    private var text: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        setViews()
        processIntentValues(intent)

        supportActionBar?.title = if (mIsNew) "New Note" else "Edit Note"
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

    private fun setViews() {
        title = findViewById(R.id.note_title)
        text = findViewById(R.id.note_body)
    }

    private fun processIntentValues(intent: Intent) {
        // get values from intent
        mIsNew = intent.getBooleanExtra(IS_NEW, true)
        mId = intent.getIntExtra(ID, -1)
        mStoredTitle = intent.getStringExtra(TITLE)
        mStoredText = intent.getStringExtra(TEXT)

        if (mId == -1) {
            // ToDo: Log ERROR
        }

        title?.setText(mStoredTitle)
        text?.setText(mStoredText)
    }
}
