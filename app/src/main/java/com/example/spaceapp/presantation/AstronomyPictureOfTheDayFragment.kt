package com.example.spaceapp.presantation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spaceapp.databinding.FragmentAstronomyPictureOfTheDayBinding
import com.example.spaceapp.domain.model.AstronomyPictureParam
import com.example.spaceapp.presantation.viewmodel.AstronomyPictureViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate

class AstronomyPictureOfTheDayFragment : Fragment() {
    private val viewModel by viewModel<AstronomyPictureViewModel>()
    private lateinit var binding: FragmentAstronomyPictureOfTheDayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAstronomyPictureOfTheDayBinding.inflate(
            inflater,
            container,
            false
        )

        viewModel.astronomyPicture.observe(requireActivity()) {
            binding.imageView3.setImageDrawable(it.picture)
            binding.description.text = it.description
            binding.descriptionTitle.text = it.title

            binding.progressBar.visibility = View.GONE
        }

        viewModel.loadPicture(AstronomyPictureParam(LocalDate.now(), 1))

        return binding.root
    }

    companion object {
        @JvmStatic fun newInstance() : Fragment = AstronomyPictureOfTheDayFragment()
    }
}