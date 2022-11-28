package com.jojjenator.androidfundamentals.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jojjenator.androidfundamentals.R
import com.jojjenator.androidfundamentals.data.Todo

// After making the innerclass:
// 6. This adapter now needs to know which data it needs to set to which item
// - we need to create another class that describes one item -> data class todo.

// This class is meant to hold the views(plural)
// 7. In the constructor, pass in a list of the newly created data-class.
class TodoAdapter ( // 9. When 8 is done you will have to implement members from recyclerview-adapter
    var todos: List<Todo>,


    ) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() { // 8. We now need to define that our TodoAdapter is a recyclerview-adapter.

    // 1. Each adapter for recyclerview needs to have a inner class which is a ViewHolder-class
    // 2. As the name; the view holder is used to hold the views for the recyclerview
    // 3. In our case will hold item_todo.xml

    // 4. In the constructor you want to pass a view that describes our item_todo.xml
    // 5. In constructor of inherited RecyclerView we need to pass the same itemView basically
    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // 10. These are the functions you must implement.

    // onCreateViewHolder: called when recyclerview needs a new ViewHolder.
    // Ex: The user scrolls a bit so that a new item was recycled and we need to create a new visible view.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        // 11. Create view to pass to returned TodoViewHolder
        // "this" as context wont work since not inside activity, class don't know current context
        // Since we have parent-param which is ViewGroup(=parent layout) we can get context there.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false) // never attachToRoot = true for recyclerView.

        // As we see from return-type we need to return a TodoViewHolder
        // 12. We can pass the newly created view in the constructor.
        return TodoViewHolder(view)
    }

    // getItemCount: Returns how many items we have in our recyclerview.
    // In most of the cases you want to return size of the list you passed to the constructor of adapter-class.
    override fun getItemCount(): Int {
        return todos.size
    }

    // onBindViewHolder: Used to take data from todos(the list) and set it to corresponding view
    // Params: holder - accessing the views inside the ViewHolder(textview and checkbox)
    // Params: position - the current index of the particular view we are binding, will make sense.
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        // 13. ItemView is the view that contains all of our single views inside of our item_todo.xml.
        // 14. Now we go implement this class, in our case in SecondActivity.kt

        // Since we are not using view-binding this is the way.. check that out if you would like a cleaner implementation.
        val tvTitle = holder.itemView.findViewById<TextView>(R.id.tvTitle)
        val cbDone = holder.itemView.findViewById<CheckBox>(R.id.cbDone)
        holder.itemView.apply {
            tvTitle.text = todos[position].title
            cbDone.isChecked = todos[position].isChecked
        }
    }
}