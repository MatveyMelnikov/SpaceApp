package com.example.spaceapp.presantation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.spaceapp.databinding.FragmentNearEarthAsteroidInformationBinding
import com.example.spaceapp.domain.model.AsteroidInformationParam
import com.example.spaceapp.presantation.viewmodel.AsteroidInfoViewModel
import java.time.LocalDate

class NearEarthAsteroidInformationFragment : Fragment() {
    private lateinit var viewModel: AsteroidInfoViewModel
    private lateinit var binding: FragmentNearEarthAsteroidInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNearEarthAsteroidInformationBinding.inflate(
            inflater,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity())[AsteroidInfoViewModel::class.java]

        viewModel.asteroidInfo.observe(requireActivity()) {
            binding.name.text = "Name: ${it.name}"
            binding.asteroidClass.text = "Asteroid class: ${it.asteroidClass}"
            binding.solutionDate.text = "Solution date: ${it.solutionDate.toString()}"
            binding.dataArcSpan.text = "Data-arc span: ${it.dataArcSpan.toString()}"
            binding.planetaryEphem.text = "Planetary ephem: ${it.planetaryEphem}"
            binding.sbPertEphem.text = "SB-pert ephem: ${it.sbEphem}"
            binding.conditionCode.text = "Condition code: ${it.conditionCode.toString()}"
        }

        viewModel.loadInfo(AsteroidInformationParam(
            LocalDate.of(2013, 6, 6),
            LocalDate.now()
        ))

        return binding.root
    }

    companion object {
        @JvmStatic fun newInstance() : Fragment = NearEarthAsteroidInformationFragment()
    }
}