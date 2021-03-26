package com.example.nytimestask.ui.details.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.nytimestask.R
import com.example.nytimestask.data.local.DBHelperImp
import com.example.nytimestask.data.model.Article
import com.example.nytimestask.data.remote.ApiHelperImpl
import com.example.nytimestask.data.remote.RetrofitClient
import com.example.nytimestask.data.repository.ArticlesRepository
import com.example.nytimestask.ui.details.viewmodel.DetailsViewModel
import com.example.nytimestask.ui.details.viewmodel.DetailsViewModelFactory
import com.example.nytimestask.utils.Status
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_articles.*
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {
    private val args: DetailsFragmentArgs by navArgs()
    private  val TAG = "DetailsFragment"
    private lateinit var detailsViewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_details, container, false)
        Log.i(TAG,"article id is :${args.articleID}")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpObserver()
    }


    private fun setUpViewModel(){
        detailsViewModel = ViewModelProviders.of(
            this,
            DetailsViewModelFactory(
                ArticlesRepository(
                    ApiHelperImpl(RetrofitClient.apiService),
                    DBHelperImp()
                ),
                args.articleID
            )
        ).get(DetailsViewModel::class.java)
    }

    private fun setUpObserver(){
        detailsViewModel.getArticleDetails().observe(requireActivity(),{
            when(it.status){
                Status.SUCCESS -> {
                    detailsProgressBar.visibility = GONE
                    it.data?.let { article ->
                        updateUi(article)
                        Log.i(TAG,article.toString())
                    }
                }
                Status.LOADING -> {
                    detailsProgressBar.visibility = VISIBLE
                    views.visibility = GONE
                    Log.i(TAG, "Loading.....")
                }
                Status.ERROR -> {
                    detailsProgressBar.visibility = GONE
                    views.visibility = GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    Log.i(TAG, it.message.toString())
                }
            }
        })
    }

    private fun updateUi(article: Article) {
        views.visibility = VISIBLE
        if(article.media.size != 0 ) {
            Picasso.get()
                .load(article.media[0].mediaMetadata[1].url)
                .into(articleDetailsImageView)
        }
        articleDetailsTitleTv.text = article.title
        articleDetailsSourceTv.text = article.source
        articleDetailsByLineTv.text = article.byline
        articleDetailsDateTv.text = article.publishedDate
        articleDetailsSectionTv.text = article.section
        articleDetailsKeywordsTv.text = article.adxKeywords.replace(";",",")

    }


}