package com.mready.dice.ui.history

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mready.dice.R
import com.mready.dice.storage.Storage
import com.mready.dice.ui.MainActivity

class HistoryFragment : Fragment() {

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }

    private lateinit var toolbar: Toolbar
    private lateinit var rvHistory: RecyclerView
    private lateinit var tv_title: TextView
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var storage: Storage
    private lateinit var imagineZaruri: ImageView
    private lateinit var text1Zaruri: TextView
    private lateinit var text2Zaruri: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewsReferences(view)

        toolbar.setNavigationOnClickListener {
            (requireActivity() as MainActivity).navigateBack()
        }

        val results = mutableListOf<String>()
        val preferences = (requireActivity() as MainActivity).getSharedPreferences()
        storage = Storage(preferences)
        if(storage.hasElements()) {
            tv_title.setVisibility(View.VISIBLE)
            rvHistory.setVisibility(View.VISIBLE)

            imagineZaruri.setVisibility(View.INVISIBLE)
            text1Zaruri.setVisibility(View.INVISIBLE)
            text2Zaruri.setVisibility(View.INVISIBLE)

            storage.getJsonRaw().asReversed().forEach {
                if (it.dubla) results.add("${it.total}~${it.zar1}-${it.zar2}~Dublă")
                else results.add("${it.total}~${it.zar1}-${it.zar2}~")
            }

            historyAdapter = HistoryAdapter(results)
            rvHistory.adapter = historyAdapter
        }
        else{
            tv_title.setVisibility(View.INVISIBLE)
            rvHistory.setVisibility(View.INVISIBLE)

            imagineZaruri.setVisibility(View.VISIBLE)
            text1Zaruri.setVisibility(View.VISIBLE)
            text2Zaruri.setVisibility(View.VISIBLE)
        }
    }

    private fun getViewsReferences(view: View) {
        toolbar = view.findViewById(R.id.toolbar)
        rvHistory = view.findViewById(R.id.rv_history)
        tv_title = view.findViewById(R.id.tv_title)

        imagineZaruri = view.findViewById(R.id.hv_zaruri)
        text1Zaruri = view.findViewById(R.id.hv_notification)
        text2Zaruri = view.findViewById(R.id.hv_notification_details)
    }

}