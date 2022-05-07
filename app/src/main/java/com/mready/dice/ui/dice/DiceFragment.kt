package com.mready.dice.ui.dice

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.mready.dice.R
import com.mready.dice.ui.MainActivity

class DiceFragment : Fragment() {
    companion object {
        fun newInstance(): DiceFragment {
            return DiceFragment()
        }
    }

    private lateinit var firstDice: ImageView
    private lateinit var secondDice: ImageView
    private lateinit var containerHistory: View
    private lateinit var rollButton: MaterialButton
    private var diceImages: List<Int> = listOf(
        R.drawable.dice_face_01,
        R.drawable.dice_face_02,
        R.drawable.dice_face_03,
        R.drawable.dice_face_04,
        R.drawable.dice_face_05,
        R.drawable.dice_face_06
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewsReferences(view)

        containerHistory.setOnClickListener {
            (requireActivity() as MainActivity).navigateToHistory()
        }
        rollButton.setOnClickListener {
            rollDice(view)
        }
    }

    private fun getViewsReferences(view: View) {
        containerHistory = view.findViewById(R.id.container_history)
        rollButton = view.findViewById(R.id.btn_roll)

        firstDice = view.findViewById<ImageView>(R.id.iv_first_die)
        secondDice = view.findViewById<ImageView>(R.id.iv_second_die)
    }

    fun rollDice(view: View){
        firstDice.setImageResource(diceImages.random());
        secondDice.setImageResource(diceImages.random());
    }
}