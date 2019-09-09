package br.com.spacexlaunches.base

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    // Lifecycle methods

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    // Protected methods

    protected fun showToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
