package com.example.textquest.presentation.customviews

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.textquest.R

class AlertDialogName : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater

            builder.setView(inflater.inflate(R.layout.dialog_form, null))
                .setPositiveButton("Ok") { dialog, which ->

                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}