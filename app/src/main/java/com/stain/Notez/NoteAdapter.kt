package com.stain.Notez

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.stain.Notez.models.Note

class NoteAdapter(private val ctx: Context, private val notes: ArrayList<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    // ToDo: find better name for mainActivity!

//    private val clickListener = View.OnClickListener { view ->
//        val item = view.tag as Note
//        val context = view.context
//
//        // code for showing / hiding text of the note!
//        // See linkedIn tutorial.
//    }

    interface ItemClickListener {
        fun onEditClicked(adapterPos: Int)
        fun onDelClicked(adapterPos: Int)
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(icl: ItemClickListener) {
        itemClickListener = icl
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.note_row, parent, false)
        return ViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = notes[position].title
        holder.timestamp.text = Util.getTimestampText(notes[position].timestamp)

        // ToDo: use the following line to handling clicking on the entire row + use clickListener above
//        holder.itemView.setOnClickListener()
    }

    override fun getItemCount(): Int = notes.size

    class ViewHolder(itemView: View, clickListener: ItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val timestamp: TextView = itemView.findViewById(R.id.timestamp)
        private val btnEdit: ImageButton = itemView.findViewById(R.id.btn_edit)
        private val btnDel: ImageButton = itemView.findViewById(R.id.btn_del)

        init {
            btnEdit.setOnClickListener {
                clickListener.onEditClicked(adapterPosition)
            }
            btnDel.setOnClickListener {
                clickListener.onDelClicked(adapterPosition)
            }
        }
    }

    fun itemChanged(note: Note) {
        val index = getItemIndex(note.id)
        notes[index] = note
        notifyItemChanged(index)
    }

    fun itemAdded(note: Note) {
        notes.add(note)
        notifyItemInserted(notes.size - 1)
    }

    fun itemRemoved(position: Int) {
        notes.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItem(position: Int): Note {
        return notes[position]
    }

    private fun getItemIndex(id: Int): Int {
        var itemIndex = -1
        for ((index, value) in notes.withIndex()) {
            if (value.id == id) {
                itemIndex = index
                break
            }
        }
        return itemIndex
    }

}