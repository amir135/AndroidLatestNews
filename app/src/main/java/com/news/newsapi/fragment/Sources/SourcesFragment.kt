package com.news.newsapi.fragment.Sources

import android.os.Bundle
import android.view.*
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.newsapi.MainActivity
import com.news.newsapi.R
import com.news.newsapi.data.sources.Sources
import com.news.newsapi.databinding.SourcesFragmentBinding
import com.news.newsapi.di.Injectable
import com.news.newsapi.di.injectViewModel
import kotlinx.android.synthetic.main.news_fragment.*
import kotlinx.android.synthetic.main.sources_fragment.*
import kotlinx.android.synthetic.main.sources_fragment.progressBar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class SourcesFragment : Fragment(), Injectable,SourcesAdapter.Callback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SourcesViewModel


    private lateinit var layoutManager: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        val binding = SourcesFragmentBinding.inflate(inflater, container, false)
        context ?: binding.root
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(requireContext())
        unitList.layoutManager = layoutManager

        viewModel.getSources().observe(viewLifecycleOwner, androidx.lifecycle.Observer
        {
            t ->
                run {
                    unitList.adapter = context?.let { SourcesAdapter(t.toMutableList(),this) }
                    progressBar.visibility=View.GONE
                }
        })
        GlobalScope.async {
            viewModel.fetchSources()
        }

    }

    override fun onSelect(source: Sources) {
       // show sources url in webView todo
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        (requireActivity() as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                (requireActivity() as MainActivity).setSideMenuIcon()
                requireFragmentManager().popBackStack()
                return true
            }        }
        return super.onOptionsItemSelected(item)
    }

}
