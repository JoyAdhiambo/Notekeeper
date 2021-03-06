package com.example.notekeeper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notereader20.NoteInfo

class NoteRecyclerAdapter(private val context:Context, private val notes: ArrayList<NoteInfo>):
    RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>(){

    private val layoutInflater = LayoutInflater.from(context)
    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val textCourse = itemView?.findViewById<TextView>(R.id.textCourse)
        val textTitle = itemView?.findViewById<TextView>(R.id.textTittle)

        var notePosition = 0
        init {
            itemView?.setOnClickListener {
                val intent = Intent(context, NoteListActivity::class.java)
                intent.putExtra(EXTRA_NOTE_POSITION, notePosition)
                context.startActivity(intent)
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
  val itemView = layoutInflater.inflate(R.layout.item_note_list,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val note = notes[position]
      holder.textCourse?.text =   note.course?.tittle
        holder.textTitle?.text = note.title
        holder.notePosition = position
    }

    override fun getItemCount() =notes.size

}