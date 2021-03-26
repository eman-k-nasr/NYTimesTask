package com.example.nytimestask.ui.details.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.nytimestask.R

class DetailsFragment : Fragment() {
    private val args: DetailsFragmentArgs by navArgs()
    private  val TAG = "DetailsFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_details, container, false)
        Log.i(TAG,"article id is :${args.articleID}")
        return root
    }
}