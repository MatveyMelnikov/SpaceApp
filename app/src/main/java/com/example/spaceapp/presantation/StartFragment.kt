package com.example.spaceapp.presantation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.spaceapp.R
import com.example.spaceapp.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(
            inflater,
            container,
            false
        )

        binding.APOD.setOnClickListener {
//            val fragmentManager = parentFragmentManager
//            val transaction = fragmentManager.beginTransaction()
//            transaction.replace(
//                R.id.fragmentContainerView,
//                AstronomyPictureOfTheDayFragment.newInstance()
//            )
//            transaction.addToBackStack(null)
//            transaction.commit()

            val navController = requireActivity().findNavController(R.id.fragmentContainerView)
            navController.navigate(
                R.id.action_startFragment_to_astronomyPictureOfTheDayFragment
            )
        }

        binding.NEARI.setOnClickListener {

            val navController = requireActivity().findNavController(R.id.fragmentContainerView)
            navController.navigate(
                R.id.action_startFragment_to_nearEarthAsteroidInformationFragment
            )
        }

        return binding.root
    }
}