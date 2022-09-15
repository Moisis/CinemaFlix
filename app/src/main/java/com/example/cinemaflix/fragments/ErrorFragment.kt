package com.example.cinemaflix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.cinemaflix.R
import org.w3c.dom.Text


class ErrorFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_error, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val error : Button = view.findViewById(R.id.errortext)
        error.setOnClickListener{
            val home : Fragment = HomeFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentholder, home)
            transaction.commit()
        }
    }
}