package com.example.testtaskfore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.testtaskfore.databinding.DetailsFragmentBinding
import com.example.testtaskfore.utils.CoilImageLoader

class DetailsFragment : Fragment() {
    private val sharedViewModel: PhotoViewModel by activityViewModels()
    private lateinit var binding: DetailsFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindPhoto()
    }
    // TODO: to refactor null and duplicates
    private fun bindPhoto() {
        sharedViewModel.currentPhoto.value?.urls?.full?.let {
            CoilImageLoader.loadImage(binding.ivDetailedImage, it)
        }
        binding.tvAuthorName.text = sharedViewModel.currentPhoto.value?.description
        binding.tvAuthorName.text = sharedViewModel.currentPhoto.value?.user?.name
        binding.tvAuthorName.text = sharedViewModel.currentPhoto.value?.user?.name

    }

}