package com.example.textquest.presentation.customviews

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.textquest.R
import com.example.textquest.presentation.viewmodels.DialogInteract

class AlertDialogName(private val dialogInteract: DialogInteract) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater
            val layout = inflater.inflate(R.layout.dialog_form, null)

            builder.setView(layout)
                .setPositiveButton("Ok") { _, _ ->
                    val et = layout.findViewById<EditText>(R.id.name)
                    dialogInteract.onDialogInteract(et.text.toString())
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}