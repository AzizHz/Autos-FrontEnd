package com.example.autos.ui.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.autos.R
import com.example.autos.adapters.PostAdapter
import com.example.autos.api.ApiInterface
import com.example.autos.data.Post
import com.example.autos.databinding.FragmentHomePageBinding
import com.example.autos.helpers.BannerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class HomePageFragment:Fragment(R.layout.fragment_home_page) {
    lateinit var binding: FragmentHomePageBinding
    private val  imageList= mutableListOf<Int>()
    private lateinit var adapter:BannerViewAdapter
    private lateinit var searchView: SearchView
    private lateinit var viewPager2: ViewPager2
    lateinit var gridLayoutManager: GridLayoutManager
    private var postList = ArrayList<Post>()
    lateinit var rvPosts: RecyclerView
    lateinit var postAdapter: PostAdapter

private val  viewPager2OnPageChangeCallback=object :ViewPager2.OnPageChangeCallback(){
var currentIndex=-1
val runnable= Runnable {
    viewPager2.setCurrentItem(viewPager2.currentItem+1,true)
}

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        currentIndex =position
        viewPager2.removeCallbacks(runnable)
        viewPager2.postDelayed(runnable,2500)
    }

    override fun onPageScrollStateChanged(state: Int) {
        super.onPageScrollStateChanged(state)
        if (state== ViewPager.SCROLL_STATE_IDLE){
            if (currentIndex==0){
                viewPager2.setCurrentItem(imageList.size-2,false)

            }
            else if(currentIndex==imageList.size-1){
                viewPager2.setCurrentItem(1,false)
            }
        }
    }
}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomePageBinding.inflate(inflater)

        viewPager2=binding.root.findViewById(R.id.viewPager2)

        rvPosts=binding.root.findViewById(R.id.rvProduct)
        rvPosts.setHasFixedSize(true)
        searchView=binding.root.findViewById(R.id.search_bar)
        gridLayoutManager= GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
        rvPosts.layoutManager=gridLayoutManager
        ApiInterface.create().getPosts().enqueue(object : Callback<List<Post>?> {
            override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
                val responseBody=response.body()!!



                postList.addAll(responseBody)
                // postAdapter= PostAdapter(requireContext(),responseBody)
                //
                //rvPosts.adapter=postAdapter

            }

            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
        postAdapter= PostAdapter(requireContext(),postList)
        postAdapter.notifyDataSetChanged()
        rvPosts.adapter=postAdapter

        initviewPager2()



        return binding.root
    }



    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<Post>()
            for (i in postList) {
                if (i.Title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                postAdapter.setFilteredList(filteredList)
            }
        }
        else{
            postAdapter.setFilteredList(postList)
        }
    }

    private fun initviewPager2(){
        imageList.clear()
        imageList.add(R.drawable.pic_seven)
        imageList.add(R.drawable.pic_six)
        imageList.add(R.drawable.pic_five)
        imageList.add(R.drawable.pic_seven)
        imageList.add(R.drawable.pic_six)
        adapter= BannerViewAdapter()
        viewPager2.adapter=adapter
        viewPager2.clipChildren=false
        viewPager2.offscreenPageLimit=3

        //val paddingEnd=(getScreenWidthPx(requireContext())-dpToPx(requireContext(),300f)

        adapter.reload(imageList)
        val compositePageTransformer=CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->

        }
        viewPager2.setPageTransformer(compositePageTransformer)
        viewPager2.registerOnPageChangeCallback(viewPager2OnPageChangeCallback)
        viewPager2.currentItem=1
    }

    override fun onDestroy() {
        viewPager2.unregisterOnPageChangeCallback(viewPager2OnPageChangeCallback)
        super.onDestroy()

    }

    private fun getScreenWidthPx(context:Context):Int{
        val outMetrics=DisplayMetrics()
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
            val display=context.display
            display?.getRealMetrics(outMetrics)
        }
        else{
            @Suppress("DEPRECATION")
            val display=(context.getSystemService(Context.WINDOW_SERVICE)as WindowManager).defaultDisplay
            @Suppress("DEPRECATION")
            display.getMetrics(outMetrics)
        }
        return outMetrics.widthPixels
    }
    private fun dpToPx(context: Context,dpValue: Float):Int{
        return (dpValue*(context.resources.displayMetrics.density)+0.5f).toInt()
    }

}