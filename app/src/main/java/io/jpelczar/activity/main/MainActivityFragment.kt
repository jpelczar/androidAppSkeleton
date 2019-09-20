package io.jpelczar.activity.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.jpelczar.R
import io.jpelczar.databinding.FragmentMainBinding

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    private lateinit var mainFragmentViewModel: MainFragmentViewModel
    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainFragmentViewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        mainFragmentViewModel.execute()

        return binding.root
    }
}
