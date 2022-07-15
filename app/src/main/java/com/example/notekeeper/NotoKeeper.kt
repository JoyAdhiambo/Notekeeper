package com.example.notereader20

 data class  CourseInfo(val courseid: String, val tittle : String) {
  override fun toString(): String {
   return tittle
  }
 }

data class NoteInfo(var course: CourseInfo? = null, var title: String? = null, var text: String? = null)