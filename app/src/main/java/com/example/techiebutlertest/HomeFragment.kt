package com.example.techiebutlertest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techiebutlertest.databinding.FragmentHomeBinding
import com.example.techiebutlertest.domain.entities.PostDisplayData
import com.example.techiebutlertest.presentation.ItemClickCallBack
import com.example.techiebutlertest.presentation.LoadItemAdapter
import com.example.techiebutlertest.presentation.PostAdapter
import com.example.techiebutlertest.presentation.PostComparator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickCallBack {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val postAdapter = PostAdapter(PostComparator(), this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPostDataList()
        setRetryAndLoaderUI()
        setListener()
    }

    private fun initPostDataList() {
        binding.recyclerView.adapter = postAdapter.withLoadStateFooter(
            LoadItemAdapter(postAdapter::retry)
        )
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setItemViewCacheSize(10)
    }

    private fun setRetryAndLoaderUI() {
        binding.retryBtn.setOnClickListener {
            postAdapter.retry()
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                postAdapter.loadStateFlow.collectLatest {
                    binding.itemLoader.isVisible = it.refresh is LoadState.Loading
                    binding.errorLayout.isVisible = it.refresh is LoadState.Error
                }
            }
        }
    }

    private fun setListener() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.posts.collectLatest {
                    postAdapter.submitData(it)
                }
            }
        }
    }

    override fun onPostClick(post: PostDisplayData) {
        findNavController().navigate(R.id.action_HomeFragment_to_DetailFragment, Bundle().apply {
            putString(DetailFragment.POST_TITLE, post.title.toString())
            putString(DetailFragment.POST_BODY, post.body.toString())
            putInt(DetailFragment.POST_ID, post.id)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}