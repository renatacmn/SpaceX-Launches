package br.com.spacexlaunches.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.spacexlaunches.R
import br.com.spacexlaunches.base.BaseActivity
import br.com.spacexlaunches.base.api.models.Launch
import br.com.spacexlaunches.util.setFormattedDateOnTextView
import br.com.spacexlaunches.util.setLaunchStatusOnTextView
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {

    private var launch: Launch? = null

    // Lifecycle methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getExtras()
        initializeViewComponents()
    }

    // Private methods

    private fun getExtras() {
        launch = intent.extras?.getParcelable(PARAM_LAUNCH)
    }

    private fun initializeViewComponents() {
        initializeLaunchInfo()
    }

    private fun initializeLaunchInfo() {
        launch?.setLaunchStatusOnTextView(detailsTextStatus)
        detailsTextName.text = launch?.missionName
        launch?.setFormattedDateOnTextView(detailsTextDate)
    }

    companion object {
        fun start(context: Context, launch: Launch) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(PARAM_LAUNCH, launch)
            context.startActivity(intent)
        }
    }

}
