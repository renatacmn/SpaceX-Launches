package br.com.spacexlaunches.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.com.spacexlaunches.R
import br.com.spacexlaunches.base.BaseFragment
import br.com.spacexlaunches.base.api.models.Launch
import br.com.spacexlaunches.util.ImageLoader
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list_state_data.*
import javax.inject.Inject

class ListFragment : BaseFragment(), ListAdapter.Listener {

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    private val viewModel: ListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
    }

    private var observer = Observer(this::updateUi)
    private var adapter: ListAdapter? = null

    // Lifecycle methods

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewComponents()
        observeListViewState()
        viewModel.getAllLaunches()
    }

    // ListAdapter.Listener override

    override fun onLaunchClicked(launch: Launch) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(launch)
        findNavController().navigate(action)
    }

    // Private methods

    private fun initializeViewComponents() {
        initializeList()
        initializeSwipeToRefreshLayout()
        initializeBtnNewContent()
    }

    private fun initializeList() {
        if (adapter == null) {
            adapter = ListAdapter(this, imageLoader)
            listLaunches.adapter = adapter
        }
    }

    private fun initializeSwipeToRefreshLayout() {
        listSwipeToRefresh.setOnRefreshListener {
            viewModel.getAllLaunches()
        }
    }

    private fun initializeBtnNewContent() {
        listBtnNewContent.setOnClickListener {
            adapter?.notifyDataSetChanged()
        }
    }

    private fun observeListViewState() {
        viewModel.getListViewState().observe(this, observer)
    }

    private fun updateUi(viewState: ListViewState) {
        when (viewState) {
            is ListViewState.FirstTimeLoading -> showFirstTimeLoading()
            is ListViewState.DefaultLoading -> showDefaultLoading()
            is ListViewState.Error -> showError()
            is ListViewState.Empty -> showEmptyState()
            is ListViewState.UpdateList -> updateList(viewState.launches)
            is ListViewState.AppendToList -> appendToList(viewState.newLaunchesToAppend)
        }
    }

    private fun showFirstTimeLoading() {
        listSwipeToRefresh.isRefreshing = false
        layoutListStateLoading.visibility = View.VISIBLE
    }

    private fun showDefaultLoading() {
        listSwipeToRefresh.isRefreshing = true
        layoutListStateLoading.visibility = View.GONE
    }

    private fun showError() {
        hideLoadings()
        showToast(getString(R.string.launch_list_msg_error_state))
    }

    private fun showEmptyState() {
        hideLoadings()
        layoutListStateEmpty.visibility = View.VISIBLE
        layoutListStateData.visibility = View.GONE
    }

    private fun updateList(launches: List<Launch>) {
        hideLoadings()
        listBtnNewContent.visibility = View.GONE
        layoutListStateEmpty.visibility = View.GONE
        layoutListStateData.visibility = View.VISIBLE
        adapter?.updateAdapter(launches)
    }

    private fun appendToList(newLaunchesToAppend: List<Launch>) {
        listBtnNewContent.visibility = View.VISIBLE
        adapter?.appendToList(newLaunchesToAppend)
    }

    private fun hideLoadings() {
        listSwipeToRefresh.isRefreshing = false
        layoutListStateLoading.visibility = View.GONE
    }

}
