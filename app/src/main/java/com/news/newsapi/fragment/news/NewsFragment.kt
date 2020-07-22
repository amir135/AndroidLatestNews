package com.news.newsapi.fragment.news

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.newsapi.MainActivity
import com.news.newsapi.R
import com.news.newsapi.databinding.NewsFragmentBinding
import com.news.newsapi.di.Injectable
import com.news.newsapi.di.injectViewModel
import com.news.newsapi.util.isTablet
import kotlinx.android.synthetic.main.news_fragment.*
import kotlinx.android.synthetic.main.sources_fragment.progressBar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject


class NewsFragment : Fragment(), Injectable, NewsAdapter.Callback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsViewModel

    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        val binding = NewsFragmentBinding.inflate(inflater, container, false)
        context ?: binding.root
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsList.apply {
            if(isTablet(requireActivity())) {
                layoutManager = GridLayoutManager(context, 2)
            }
            else{

                layoutManager = GridLayoutManager(context, 1)
            }
        }

            viewModel.getAllNews().observe(viewLifecycleOwner, androidx.lifecycle.Observer
            {
                    t ->
                run {

                    newsList.adapter = context?.let { NewsAdapter(t.toMutableList(),this) }
                    progressBar.visibility=View.GONE
                }
            })

        swipeContainer.setOnRefreshListener {
            GlobalScope.async {
                viewModel.fetchNews("a", "en", null)
            }.apply { swipeContainer.isRefreshing = false; }
//            Handler().postDelayed({
//                swipeContainer.isRefreshing = false;
//            }, 2000)

        }
        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
        GlobalScope.async {
            viewModel.fetchSources()
            viewModel.fetchNews("a", "en", null)
        }.apply { swipeContainer.isRefreshing = false; }
    }

    override fun onSelect(url: String) {
        val bundle=Bundle()
        bundle.putString("newsUrl",url)
        (requireActivity() as MainActivity).navController.navigate(R.id.newsViewFragment,bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        (requireActivity() as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                (requireActivity() as MainActivity).drawerLayout.openDrawer(GravityCompat.START)

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}