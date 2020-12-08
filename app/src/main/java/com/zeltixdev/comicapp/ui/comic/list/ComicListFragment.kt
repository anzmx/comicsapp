package com.zeltixdev.comicapp.ui.comic.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.zeltixdev.comicapp.databinding.FragmentComicListBinding
import com.zeltixdev.comicapp.networking.Status
import com.zeltixdev.comicapp.ui.comic.list.adapter.ComicListAdapter
import com.zeltixdev.comicapp.ui.comic.list.adapter.GridSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ComicListFragment : Fragment() {

    private val comicListViewModel: ComicListViewModel by viewModels()
    private lateinit var comicListAdapter: ComicListAdapter

    private var _binding: FragmentComicListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComicListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUiElements()
        initViewModelCallbacks()
    }

    private fun initUiElements() {
        comicListAdapter = ComicListAdapter { comicId ->
            val action = ComicListFragmentDirections.actionListFragmentToDetailFragment(comicId)
            findNavController().navigate(action)
        }
        binding.comicList.layoutManager = GridLayoutManager(requireContext(),2)
        binding.comicList.addItemDecoration(GridSpacingItemDecoration())
        binding.comicList.adapter = comicListAdapter
    }

    private fun initViewModelCallbacks() {
        comicListViewModel.displayComicList.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    it.data?.let { data ->
                        comicListAdapter.items = data
                        binding.progressBar.visibility = View.INVISIBLE
                    } ?: run {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
                Status.ERROR -> {
                    it.data?.let { data -> comicListAdapter.items = data}
                    binding.progressBar.visibility = View.INVISIBLE
                }
                Status.SUCCESS -> {
                    comicListAdapter.items = it.data!!
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        })
    }
}