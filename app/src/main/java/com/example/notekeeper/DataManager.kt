package com.example.notereader20

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()


    init{
        initializaeCourseInfo()
        initialNotes()


    }

    fun addNote(course: CourseInfo, noteTitle: String, noteText: String): Int {
        val note = NoteInfo(course, noteTitle, noteText)
        notes.add(note)
        return notes.lastIndex
    }

    fun findNote(course: CourseInfo, noteTitle: String, noteText: String) : NoteInfo? {
        for(note in notes)
            if (course == note.course &&
                noteTitle == note.title &&
                noteText == note.text)
                return note

        return null
    }


    private fun initialNotes() {
   var course = courses["android_intents"]
        var note = NoteInfo(course, "Dynamic intent resolution",
            "Wow, intents allow components to be resolved at runtime")
        notes.add(note)
        note = NoteInfo(course, "Dele\n" +
                "     gating intents",
            "PendingIntents are powerful; they delegate much more than just a component invocation")
        notes.add(note)


    }


    private fun initializaeCourseInfo(){
        var course =CourseInfo("android Intent", "Programming with Intent")
        courses.set(course.courseid,course)

       course =CourseInfo("android Java", "Programming with Java")
        courses.set(course.courseid,course)

        course =CourseInfo("Async Android", "Android Async Programming ")
        courses.set(course.courseid,course)

        course =CourseInfo("Java Core", "Fundermentals of Java core")
        courses.set(course.courseid,course)



    }
}