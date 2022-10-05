package eu.milo4apps.notinotask.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import eu.milo4apps.notinotask.R
import eu.milo4apps.notinotask.databinding.FragmentOverviewBinding
import eu.milo4apps.notinotask.databinding.GridViewItemBinding
import eu.milo4apps.notinotask.network.Product

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)
        // val itemBinding = GridViewItemBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.productsGrid.adapter = ProductGridAdapter()

        return binding.root
    }

}
