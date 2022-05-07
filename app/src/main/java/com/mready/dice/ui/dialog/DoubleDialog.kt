package com.mready.dice.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.DialogFragment
import com.mready.dice.R

class DoubleDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val dialogView = requireActivity().layoutInflater.inflate(R.layout.dialog_double, null)

        builder.setView(dialogView)

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

}