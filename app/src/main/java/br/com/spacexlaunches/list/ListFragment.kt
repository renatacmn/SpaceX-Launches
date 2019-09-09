package br.com.spacexlaunches.list

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.spacexlaunches.R
import br.com.spacexlaunches.base.api.models.Launch
import br.com.spacexlaunches.util.ImageLoader
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    private val viewModel: ListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
    }

    // Lifecycle methods

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listViewState.observe(this, Observer(this::updateUi))
    }

    // Private methods

    private fun updateUi(viewState: ListViewState) {
        when (viewState) {
            is ListViewState.Loading -> showLoading()
            is ListViewState.Error -> showError(viewState.message)
            is ListViewState.Empty -> showEmptyState()
            is ListViewState.Success -> showList(viewState.launches)
        }
    }

    private fun showLoading() {
        layoutListLoading.visibility = View.VISIBLE
        layoutListEmptyState.visibility = View.GONE
        listLaunches.visibility = View.GONE
    }

    private fun showError(message: String?) {
        layoutListLoading.visibility = View.GONE
        layoutListEmptyState.visibility = View.GONE
        listLaunches.visibility = View.GONE
        showErrorDialog(message)
    }

    private fun showEmptyState() {
        layoutListLoading.visibility = View.GONE
        layoutListEmptyState.visibility = View.VISIBLE
        listLaunches.visibility = View.GONE
    }

    private fun showList(launches: List<Launch>) {
        layoutListLoading.visibility = View.GONE
        layoutListEmptyState.visibility = View.GONE
        listLaunches.visibility = View.VISIBLE
        listLaunches.adapter = ListAdapter(imageLoader, launches)
    }

    private fun showErrorDialog(message: String?) {
        AlertDialog.Builder(context)
            .setTitle(R.string.error_dialog_title)
            .setMessage(message)
            .create()
            .show()
    }

}
