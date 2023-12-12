package com.example.a08_navigation_01.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a08_navigation_01.R
import android.widget.TextView
import com.example.a08_navigation_01.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {



    private var _binding: FragmentAboutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_about, container, false)

        var text_about = root.findViewById<TextView>(R.id.text_about)
        text_about.setText(R.string.message_home)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}