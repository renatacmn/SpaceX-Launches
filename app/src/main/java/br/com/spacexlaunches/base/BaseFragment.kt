package br.com.spacexlaunches.base

import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.Fragment
import br.com.spacexlaunches.R
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    // Lifecycle methods

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    // Protected methods

    protected fun showErrorDialog(message: String?) {
        AlertDialog.Builder(context)
            .setTitle(R.string.error_dialog_title)
            .setMessage(message)
            .create()
            .show()
    }

}
