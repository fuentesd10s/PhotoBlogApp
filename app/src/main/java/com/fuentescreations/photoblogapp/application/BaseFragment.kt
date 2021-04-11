package com.fuentescreations.photoblogapp.application

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

open class BaseFragment(view: Int) : Fragment(view) {
    private lateinit var toast: Toast
    private lateinit var snackbar: Snackbar

    fun mToast(message: String, duration: Int = Toast.LENGTH_LONG) {
        if (this::toast.isInitialized) {
            toast.cancel()
        }

        toast = Toast.makeText(requireContext(), message, duration)

        toast.show()
    }

    fun mSnackbar(rootView: View, message: String) {

        if (this::snackbar.isInitialized && message.isEmpty()) {
            snackbar.dismiss()
        } else if (message.isNotEmpty()) {
            snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_INDEFINITE)
            snackbar.show()
        }
    }
}