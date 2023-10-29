package com.example.spaceapp.presantation

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spaceapp.R
import com.example.spaceapp.databinding.FragmentNearEarthAsteroidInformationBinding
import com.example.spaceapp.domain.model.AsteroidInformationParam
import com.example.spaceapp.presantation.viewmodel.AsteroidInfoViewModel
import com.example.spaceapp.presantation.viewmodel.AsteroidInfoViewModelFactory
import java.text.SimpleDateFormat
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class NearEarthAsteroidInformationFragment : Fragment() {
    private lateinit var viewModel: AsteroidInfoViewModel
    private lateinit var binding: FragmentNearEarthAsteroidInformationBinding

    private val currentDate: Calendar = Calendar.getInstance()
    private var year = currentDate[Calendar.YEAR]
    private var month = currentDate[Calendar.MONTH]
    private var day = currentDate[Calendar.DAY_OF_MONTH]

    private val formatter = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd",
        Locale.ENGLISH)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNearEarthAsteroidInformationBinding.inflate(
            inflater,
            container,
            false
        )

        viewModel = ViewModelProvider(
            requireActivity(),
            AsteroidInfoViewModelFactory(requireContext())
        )[AsteroidInfoViewModel::class.java]

        binding.asteroidsList.adapter = AsteroidRecyclerAdapter(requireContext())
        binding.asteroidsList.layoutManager = LinearLayoutManager(requireActivity())

        binding.selectedDate.setOnClickListener {
            selectDate()
        }

        viewModel.asteroidInfo.observe(requireActivity()) {
            (binding.asteroidsList.adapter as? AsteroidRecyclerAdapter)?.data = it
            binding.asteroidsList.visibility = View.VISIBLE
            binding.progressBar2.visibility = View.GONE
            binding.selectedDate.text = it[0].closeApproachDate.format(formatter)
        }

        return binding.root
    }

    private fun selectDate() {
        val mDatePicker = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                try {
                    val selectedDate = LocalDate.of(selectedYear, selectedMonth, selectedDay)
                    day = selectedDay
                    month = selectedMonth
                    year = selectedYear
                    currentDate.set(year, month, day)
                    binding.selectedDate.text = selectedDate.format(formatter)
                    viewModel.loadInfo(AsteroidInformationParam(selectedDate))
                    binding.progressBar2.visibility = View.VISIBLE
                } catch (e: DateTimeException) {
                    Toast.makeText(
                        requireContext(), "Invalid date!", Toast.LENGTH_SHORT
                    ).show()
                }

            }, year, month, day
        )

        mDatePicker.show()
    }

    companion object {
        @JvmStatic fun newInstance() : Fragment = NearEarthAsteroidInformationFragment()
    }
}