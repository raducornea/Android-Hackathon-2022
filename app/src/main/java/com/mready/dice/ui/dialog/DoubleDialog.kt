package com.mready.dice.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.mready.dice.R

class DoubleDialog : DialogFragment() {
    private lateinit var thxButton: MaterialButton

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val dialogView = requireActivity().layoutInflater.inflate(R.layout.dialog_double, null)

        builder.setView(dialogView)

        getViewsReferences(dialogView)
        thxButton.setOnClickListener {
            quitView()
        }

        return builder.create().also {
            it.window?.setBackgroundDrawable(
                AppCompatResources.getDrawable(
                    it.context,
                    R.drawable.bg_popup_inset
                )
            )
            it.window?.requestFeature(Window.FEATURE_NO_TITLE)
        }
    }

    private fun getViewsReferences(view: View) {
        thxButton = view.findViewById(R.id.btn_thx)
    }

    private fun quitView(){
        dismiss()
    }

}