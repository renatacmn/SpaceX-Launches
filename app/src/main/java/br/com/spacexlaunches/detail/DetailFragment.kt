package br.com.spacexlaunches.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.spacexlaunches.R
import br.com.spacexlaunches.base.BaseFragment
import br.com.spacexlaunches.base.api.models.Launch
import br.com.spacexlaunches.util.setFormattedDateOnTextView
import br.com.spacexlaunches.util.setLaunchStatusOnTextView
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : BaseFragment() {

    private lateinit var launch: Launch

    // Lifecycle methods

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getExtras()
        initializeViewComponents()
    }

    // Private methods

    private fun getExtras() {
        arguments?.let { arguments ->
            launch = DetailFragmentArgs.fromBundle(arguments).launch
        }
    }

    private fun initializeViewComponents() {
        initializeLaunchInfo()
    }

    private fun initializeLaunchInfo() {
        launch.setLaunchStatusOnTextView(detailsTextStatus)
        detailsTextName.text = launch.missionName
        launch.setFormattedDateOnTextView(detailsTextDate)
    }

}
