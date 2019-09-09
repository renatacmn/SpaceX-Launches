package br.com.spacexlaunches.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.spacexlaunches.R
import br.com.spacexlaunches.base.BaseFragment
import br.com.spacexlaunches.base.api.models.Launch
import br.com.spacexlaunches.util.ImageLoader
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list_state_data.*
import javax.inject.Inject

class ListFragment : BaseFragment() {

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
        layoutListStateLoading.visibility = View.VISIBLE
        layoutListStateEmpty.visibility = View.GONE
        layoutListStateData.visibility = View.GONE
    }

    private fun showError(message: String?) {
        layoutListStateLoading.visibility = View.GONE
        layoutListStateEmpty.visibility = View.GONE
        layoutListStateData.visibility = View.GONE
        showErrorDialog(message)
    }

    private fun showEmptyState() {
        layoutListStateLoading.visibility = View.GONE
        layoutListStateEmpty.visibility = View.VISIBLE
        layoutListStateData.visibility = View.GONE
    }

    private fun showList(launches: List<Launch>) {
        layoutListStateLoading.visibility = View.GONE
        layoutListStateEmpty.visibility = View.GONE
        layoutListStateData.visibility = View.VISIBLE
        listLaunches.adapter = ListAdapter(context, imageLoader, launches)
    }

}
