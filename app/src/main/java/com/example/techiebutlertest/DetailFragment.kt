package com.example.techiebutlertest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.techiebutlertest.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    companion object{
        const val POST_TITLE = "postTitle"
        const val POST_BODY = "postBody"
        const val POST_ID = "postId"
    }

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.title.text = requireArguments().getString(POST_TITLE)
        binding.body.text = requireArguments().getString(POST_BODY)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}