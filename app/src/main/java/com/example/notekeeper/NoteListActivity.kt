package com.example.notekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.notereader20.CourseInfo
import com.example.notereader20.DataManager
import com.example.notereader20.NoteInfo
import com.google.android.material.snackbar.Snackbar

class NoteListActivity : AppCompatActivity() {
    private val tag = this::class.simpleName
    private var notePosition = POSITION_NOT_SET


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notel_info)



        val adapterCourses = ArrayAdapter<CourseInfo>(this,




        android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinnerCourses = findViewById<Spinner>(R.id.spinner)
        spinnerCourses.adapter = adapterCourses

        notePosition = savedInstanceState?.getInt(EXTRA_NOTE_POSITION, POSITION_NOT_SET) ?:
                intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)

        if(notePosition != POSITION_NOT_SET)
            displayNote()
        else {
            createNewNote()
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex

        }
        Log.d(tag, "onCreate")


    }



    private fun createNewNote() {
        DataManager.notes.add(NoteInfo())
        notePosition = DataManager.notes.lastIndex
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        //val inflater: MenuInflater = menuInflater
      menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
           return when (item.itemId){

               R.id.Next->{
                   moveNext()

                   true
               }
               else -> super.onOptionsItemSelected(item)

           }

    }


    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(notePosition>= DataManager.notes.lastIndex){

            val menuItem = menu?.findItem(R.id.Next)
            if(menuItem!= null){

                menuItem.icon= getDrawable(R.drawable.ic_baseline_block_24)
                menuItem.isEnabled= false
            }
        }

        return super.onPrepareOptionsMenu(menu)
    }

    private fun moveNext(){
        ++notePosition
        displayNote()
        invalidateOptionsMenu()


    }

    private fun displayNote() {
        if(notePosition > DataManager.notes.lastIndex) {
            showMessage("Note not found")
            Log.e(tag, "Invalid note position $notePosition, max valid position ${DataManager.notes.lastIndex}")
            return
        }

        Log.i(tag, "Displaying note for position $notePosition")
        val note = DataManager.notes[notePosition]

        val textNoteTitle = findViewById<TextView>(R.id.textNoteTittle)
        val textNoteText = findViewById<TextView>(R.id.textNotetext)
        val spinnerCourses = findViewById<Spinner>(R.id.spinner)

        textNoteTitle.setText(note.title)
        textNoteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        spinnerCourses.setSelection(coursePosition)
    }

    private fun showMessage(message: String) {
        var textNoteTitle = findViewById<TextView>(R.id.textNoteTittle)


        Snackbar.make(textNoteTitle, message, Snackbar.LENGTH_LONG).show()
    }





    override fun onPause() {
        super.onPause()
        saveNote()
        Log.d(tag, "onPause")
    }

    private fun saveNote() {
        var textNoteTitle = findViewById<TextView>(R.id.textNoteTittle)
        var textNoteText = findViewById<TextView>(R.id.textNotetext)
        var spinnerCourses = findViewById<Spinner>(R.id.spinner)

        val note = DataManager.notes[notePosition]
        note.title = textNoteTitle.text.toString()
        note.text = textNoteText.text.toString()
        note.course = spinnerCourses.selectedItem as CourseInfo
    }
}

