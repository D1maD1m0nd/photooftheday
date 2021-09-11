package com.example.photooftheday.model.data.note_data

import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class NoteGeneration {
    companion object {
        fun generate(): ArrayList<Note> {
            val notes = ArrayList<Note>(50)
            val sizeNewItems = Random.nextInt(1, 50)
            for (i in 0..sizeNewItems) {
                notes.add(
                    Note(
                        "Item" + Random.nextInt(500, 50000),
                        "Равным образом рамки и место обучения кадров играет важную роль в" +
                                " формировании системы обучения кадров, соответствует насущным потребностям." +
                                " С другой стороны консультация с широким активом позволяет " +
                                "выполнять важные задания по разработке модели развития.",
                        Date(),
                        Random.nextInt(1, 4)
                    )
                )
            }

            return notes
        }
    }
}