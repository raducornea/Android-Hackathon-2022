package com.mready.dice.ui.dice

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.mready.dice.R
import com.mready.dice.storage.Storage
import com.mready.dice.ui.MainActivity
import com.mready.dice.ui.dialog.DoubleDialog
import kotlinx.serialization.*


class DiceFragment : Fragment() {

    companion object {
        fun newInstance(): DiceFragment {
            return DiceFragment()
        }
    }

    private lateinit var storage: Storage
    private lateinit var firstDice: ImageView
    private lateinit var secondDice: ImageView
    private lateinit var firstDiceAnimation: com.airbnb.lottie.LottieAnimationView
    private lateinit var secondDiceAnimation: com.airbnb.lottie.LottieAnimationView
    private lateinit var containerHistory: View
    private lateinit var containerHistoryText: TextView
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
        val preferences = (requireActivity() as MainActivity).getSharedPreferences()
        storage = Storage(preferences)
//        storage.addNoPreferences() // uncomment if you want to reset list

        // preia toate view-urile
        getViewsReferences(view)

        if(storage.hasElements()){
            val lastElement = storage.getLastElement()

            firstDice.setImageResource(diceImages.elementAt(lastElement.zar1 - 1))
            secondDice.setImageResource(diceImages.elementAt(lastElement.zar2 - 1))

            updateDiceHistoryText()
        }

        // seteaza pentru view-urile respective diverse functionalitati
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
        firstDiceAnimation = view.findViewById(R.id.animationViewZar1)
        secondDiceAnimation = view.findViewById(R.id.animationViewZar2)

        containerHistoryText = view.findViewById(R.id.tv_previous_roll)
    }

    fun printOnScreen(message: String){
        (requireActivity() as MainActivity).showToast(message)
    }

    fun rollDice(view: View){
        val random1 = (1..6).random()
        val random2 = (1..6).random()
        val total = random1 + random2
        val dubla = (random1 == random2)

        // timp de o secunda ar trebui sa se invizibilizeze imaginile
        firstDice.setVisibility(View.INVISIBLE)
        secondDice.setVisibility(View.INVISIBLE)

        // setam butonul ca inactiv la apasare
        rollButton.setEnabled(false)

        // se fac vizibile animatiile
        firstDiceAnimation.setVisibility(View.VISIBLE)
        secondDiceAnimation.setVisibility(View.VISIBLE)

        // sa aiba loc animatia propriu-zisa
        firstDiceAnimation.playAnimation()
        secondDiceAnimation.playAnimation()

        // dupa o secunda de cand fac animatiile, vor reveni imaginile statatoare
        Handler().postDelayed(Runnable {
            firstDiceAnimation.pauseAnimation()
            secondDiceAnimation.pauseAnimation()

            firstDiceAnimation.setVisibility(View.INVISIBLE)
            secondDiceAnimation.setVisibility(View.INVISIBLE)
            firstDice.setVisibility(View.VISIBLE)
            secondDice.setVisibility(View.VISIBLE)

            // afiseaza popup cu dubla cand a dat dubla
            if(dubla){
                (requireActivity() as MainActivity).navigateDoubleDialog()
            }

            // setam butonul ca activ acum
            rollButton.setEnabled(true)
        }, 1000)

        // schimbam imaginea resursei
        firstDice.setImageResource(diceImages.elementAt(random1 - 1));
        secondDice.setImageResource(diceImages.elementAt(random2 - 1));

        // actualizam json-ul
        storage.addElement(total, random1, random2, dubla)

        // schimbat si butonul acela suspect din dreapta sus cu ultimul zar
        if (storage.getElementsSize() > 1)
            updateDiceHistoryText()


    }

    // functie care actualizeaza Zarul Anterior Text din dreapta sus
    fun updateDiceHistoryText(){
        containerHistoryText.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
        containerHistoryText.text = "Zarul anterior\n"

        if(storage.getElementsSize() > 1) {
            val lastElement = storage.getLastLastElement()
            if (lastElement.dubla) {
//                containerHistoryText.setTextColor(Color.parseColor("#D87153"))
                val lastResult =
                    "Dublă\t" + lastElement.zar1.toString() + "-" + lastElement.zar2.toString()
                containerHistoryText.append(lastResult)
            } else {
                containerHistoryText.setTextColor(Color.parseColor("#FFFFFF"))
                val lastResult = lastElement.zar1.toString() + "-" + lastElement.zar2.toString()
                containerHistoryText.append(lastResult)
            }
        }
    }
}