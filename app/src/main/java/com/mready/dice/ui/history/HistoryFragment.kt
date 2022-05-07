package com.mready.dice.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mready.dice.R
import com.mready.dice.ui.MainActivity

class HistoryFragment : Fragment() {

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }

    private lateinit var toolbar: Toolbar
    private lateinit var rvHistory: RecyclerView

    private lateinit var historyAdapter: HistoryAdapter

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

        historyAdapter = HistoryAdapter(listOf())
        rvHistory.adapter = historyAdapter
    }

    private fun getViewsReferences(view: View) {
        toolbar = view.findViewById(R.id.toolbar)
        rvHistory = view.findViewById(R.id.rv_history)
    }

}