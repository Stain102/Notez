package com.stain.Notez

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

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

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            finish()
        }
    }
}
