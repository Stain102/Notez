package com.stain.Notez

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.stain.Notez.models.Note

class NoteAdapter(private val notes: ArrayList<Note>, private val mainActivity: MainActivity) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    // ToDo: find better name for mainActivity!

//    private val clickListener = View.OnClickListener { view ->
//        val item = view.tag as Note
//        val context = view.context
//
//        // code for showing / hiding text of the note!
//        // See linkedIn tutorial.
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.note_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = notes[position].title
        holder.timestamp.text = notes[position].timestamp

        holder.btnEdit.setOnClickListener {
            mainActivity.openNoteActivity(false, notes[position])
        }
        holder.btnDel.setOnClickListener {
            mainActivity.deleteNote(notes[position].id)
        }
        // ToDo: use the following line to handling clicking on the entire row + use clickListener above
//        holder.itemView.setOnClickListener()
    }

    override fun getItemCount(): Int = notes.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val timestamp: TextView = itemView.findViewById(R.id.timestamp)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btn_edit)
        val btnDel: ImageButton = itemView.findViewById(R.id.btn_del)
    }

}