package com.stain.Notez

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import com.stain.Notez.models.Note

class NoteActivity : AppCompatActivity() {

    companion object {
        const val IS_NEW = "is_new"
        const val NOTE = "note"

        const val REQ_CODE_ADD = 1000
        const val REQ_CODE_UPDATE = 1001
    }

    private var isNew = true
    private var title: EditText? = null
    private var text: EditText? = null
    private var timestamp: TextView? = null
    lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        setViews()
        processIntentValues(intent)

        supportActionBar?.title = if (isNew) "New Note" else "Edit Note"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.action_save) {
            saveNote()
            return true
        } else super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (hasNoteChanged()) {
            alertOfChanges()
        } else {
            super.onBackPressed()
        }
    }

    private fun alertOfChanges() {
        AlertDialog.Builder(this@NoteActivity)
                .setTitle("Note has changed!")
                .setMessage("Do you want to save the changes?")
                .setPositiveButton("Yes"){_, _ -> saveNote() }
                .setNegativeButton("No"){_, _ -> super.onBackPressed() }
                .show()
    }

    private fun saveNote() {
        val intent = Intent()

        // Avoiding saving if no changes has been made!
        if (hasNoteChanged()) {
            note.title = title?.text.toString()
            note.text = text?.text.toString()
            note.timestamp = Util.getTimestamp()
            intent.putExtra(NOTE, note)
            setResult(Activity.RESULT_OK, intent)
        } else {
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }

    private fun hasNoteChanged(): Boolean {
        val title = title?.text.toString()
        val text = text?.text.toString()

        return note.compareTo(Note(note.id, title, text, note.timestamp)) != 0
    }

    private fun setViews() {
        title = findViewById(R.id.note_title)
        text = findViewById(R.id.note_body)
        timestamp = findViewById(R.id.note_status)
    }

    private fun processIntentValues(intent: Intent) {
        // get values from intent
        // isNew - true (mainActivity), false (NoteAdapter)
        isNew = intent.getBooleanExtra(IS_NEW, true)
        note = intent.getParcelableExtra(NOTE)

        title?.setText(note.title)
        text?.setText(note.text)
        timestamp?.text = Util.getTimestampText(note.timestamp)
    }
}
