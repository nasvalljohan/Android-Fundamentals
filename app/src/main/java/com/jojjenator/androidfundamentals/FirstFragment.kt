package com.jojjenator.androidfundamentals

import androidx.fragment.app.Fragment

//Passing in the fragment-layout in the constructor is enough to inflate its own view
class FirstFragment : Fragment(R.layout.fragment_first) {

    // Lifecycle stuff below::

// onCreate also exist in fragments, but with slightly different lifecycle.
// - calling views in onCreate on fragments will make app crash because view hasn't been created yet.
// - instead onCreateView inflates the view and is called after onCreate..
// - Consider using onViewCreated since it's called when all the views is created.

}



