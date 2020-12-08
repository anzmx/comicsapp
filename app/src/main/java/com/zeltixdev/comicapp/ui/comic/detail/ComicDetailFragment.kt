package com.zeltixdev.comicapp.ui.comic.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zeltixdev.comicapp.databinding.FragmentComicDetailBinding
import com.zeltixdev.comicapp.entity.Comic
import com.zeltixdev.comicapp.entity.formattedDate
import com.zeltixdev.comicapp.entity.url
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ComicDetailFragment : Fragment() {

    private val args: ComicDetailFragmentArgs by navArgs()

    @Inject
    lateinit var photoDetailViewModelAssistedFactory: ComicDetailViewModel.AssistedFactory

    private val photoDetailViewModel: ComicDetailViewModel by viewModels {
        ComicDetailViewModel.provideFactory(
                photoDetailViewModelAssistedFactory, ComicInitParams(args.comicId)
        )
    }

    private var _binding: FragmentComicDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentComicDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModelCallbacks()
    }

    private fun initViewModelCallbacks() {
        photoDetailViewModel.apply {
            displayComic.observe(viewLifecycleOwner, { comic: Comic ->
                Glide.with(requireContext())
                    .load(comic.thumbnail.url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(binding.detailImageView)

                binding.detailTitle.text = comic.title
                binding.detailPublication.text = comic.dates.filter { it.type == "onsaleDate" }[0].formattedDate()
                binding.detailDesc.text = comic.description
                if (comic.creators.available == 0){
                    binding.detailCreators.visibility = View.GONE
                }
                else {
                    binding.detailCreators.text = comic.creators.items.joinToString("\n") {
                        it.role.toUpperCase(
                            Locale.ROOT
                        ) + ": " + it.name
                    }
                }
            })
        }
    }
}