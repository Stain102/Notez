package com.stain.Notez

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.stain.Notez.models.Note

class NoteAdapter(private val notes: ArrayList<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.note_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val id = "Id: ${notes[position].id}"
        holder.title.text = notes[position].title
        holder.id.text = id
    }

    override fun getItemCount(): Int = notes.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val id: TextView = itemView.findViewById(R.id.note_id)
    }

}