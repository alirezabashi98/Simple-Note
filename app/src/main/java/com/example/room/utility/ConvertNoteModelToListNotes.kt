package com.example.room.utility

import com.example.room.model.ListNotesModel
import com.example.room.model.NoteModels

class ConvertNoteModelToListNotes{

   fun convertNoteToListNotes(data:List<NoteModels>?):List<ListNotesModel>{
       val datas = arrayListOf<ListNotesModel>()

       data?.forEach {
           datas.add(
               ListNotesModel(it.id!!,it.title,it.note)
           )
       }

       return datas
   }

}