package com.jojjenator.androidfundamentals

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jojjenator.androidfundamentals.adapter.TodoAdapter
import com.jojjenator.androidfundamentals.data.Person
import com.jojjenator.androidfundamentals.data.Todo

class SecondActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val tvPerson = findViewById<TextView>(R.id.tvPerson)
        val btnNext = findViewById<Button>(R.id.btnOpenThirdActivity)
        val rvTodos = findViewById<RecyclerView>(R.id.rvTodos)
        val btnAddTodo = findViewById<Button>(R.id.btnAddTodo)
        val etTodo = findViewById<EditText>(R.id.etTodo)

        // getSerializableExtra does not return a Person
        // Cast it as Person to convert it back
        val person = intent.getSerializableExtra("EXTRA_PERSON") as Person

        // person now cast to Person, can now use person.name etc
        tvPerson.text = person.toString()

        btnNext.setOnClickListener {
            Intent(this, ThirdActivity::class.java).also {
                startActivity(it)
            }
        }

        // Recyclerview Below:

        // Layout item_todo.xml, todoClass and todoAdapter is parts of recyclerview.
        var todoList = mutableListOf(
            Todo("learn kotlin", false),
            Todo("learn recyclerview", false),
            Todo("eat", false),
            Todo("sleep", false),
            Todo("make bed", false),
            Todo("code", false),
        )

        // 15. First create adapter
        val adapter = TodoAdapter(todoList)
        // 16. Set the adapter of our recyclerview to our just created adapter.
        rvTodos.adapter = adapter
        // 17. define a layoutManager for our recyclerview
        rvTodos.layoutManager = LinearLayoutManager(this)// linear layoutManager

        // 18. Fix functionality of our button.
        btnAddTodo.setOnClickListener {
            val title = etTodo.text.toString()
            val todo = Todo(title, false)
            todoList.add(todo)

            // Whenever we added a new item in our list we need to update recyclerView also.
            // .add sets todoList to last index...
            adapter.notifyItemInserted(todoList.size - 1)
        }
    }
}