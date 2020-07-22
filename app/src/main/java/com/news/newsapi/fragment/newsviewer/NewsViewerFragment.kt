package com.news.newsapi.fragment.newsviewer

import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.news.newsapi.MainActivity
import com.news.newsapi.R
import com.news.newsapi.databinding.NewsViewerFragmentBinding
import com.news.newsapi.di.Injectable
import com.news.newsapi.di.injectViewModel
import com.news.newsapi.ui.hide
import kotlinx.android.synthetic.main.news_viewer_fragment.*
import java.lang.Exception
import javax.inject.Inject


class NewsViewerFragment  : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsViewerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        val binding = NewsViewerFragmentBinding.inflate(inflater, container, false)
        context ?: binding.root
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                try {
                    progressBar.hide()
                }catch (excption:Exception){}
            }
        }

        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled=true
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        val url=arguments?.get("newsUrl") as String
        webView.loadUrl(url)
        //webView.loadUrl("https://www.udemy.com/course/practical-leadership/")

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
            }
        }

        return super.onOptionsItemSelected(item)
    }

}

