package com.mready.dice.ui.history

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mready.dice.R

class HistoryAdapter(private val historyList: List<String>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val suma: TextView = view.findViewById(R.id.tv_sum)
        private val zaruri: TextView = view.findViewById(R.id.tv_zaruri)
        private val dubla: TextView = view.findViewById(R.id.tv_dubla)

        fun bind(text: String) {
            val listOfSplittedWords = text.split("~")

            if(text.contains("Dublă")){
                suma.setTextColor(Color.parseColor("#D87153"))
                zaruri.setTextColor(Color.parseColor("#D87153"))
                dubla.setTextColor(Color.parseColor("#D87153"))
            }
            else{
                suma.setTextColor(Color.parseColor("#FFFFFF"))
                zaruri.setTextColor(Color.parseColor("#FFFFFF"))
                dubla.setTextColor(Color.parseColor("#FFFFFF"))
            }

            dubla.textAlignment = View.TEXT_ALIGNMENT_VIEW_END

            suma.text = listOfSplittedWords[0]
            zaruri.text = listOfSplittedWords[1]
            dubla.text = listOfSplittedWords[2]

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)

        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyList[position])
    }

    override fun getItemCount(): Int = historyList.size

}