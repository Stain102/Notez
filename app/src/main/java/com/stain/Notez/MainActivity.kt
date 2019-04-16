package com.stain.Notez

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            openNoteActivity(false)
        }
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
}
