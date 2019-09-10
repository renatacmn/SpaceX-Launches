package br.com.spacexlaunches.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.spacexlaunches.BuildConfig
import br.com.spacexlaunches.R
import br.com.spacexlaunches.base.BaseActivity.Companion.PARAM_LAUNCH
import br.com.spacexlaunches.base.api.models.Launch
import br.com.spacexlaunches.util.GlideImageLoader
import br.com.spacexlaunches.util.setFormattedDateOnTextView
import br.com.spacexlaunches.util.setLaunchImageOrDefault
import br.com.spacexlaunches.util.setLaunchStatusOnTextView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private val imageLoader = GlideImageLoader(this)
    private var launch: Launch? = null

    // Lifecycle methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getExtras()
        initializeViewComponents()
    }

    // YouTubePlayer.OnInitializedListener overrides

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider,
        youtubePlayer: YouTubePlayer,
        b: Boolean
    ) {
        launch?.links?.youtubeId?.let { youtubeId ->
            youtubePlayer.cueVideo(youtubeId)
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider,
        youTubeInitializationResult: YouTubeInitializationResult
    ) {
        if (youTubeInitializationResult.isUserRecoverableError) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show()
        } else {
            val message = getString(R.string.details_msg_error_initializing_player)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RECOVERY_REQUEST) {
            initializeVideoView()
        }
    }

    // Private methods

    private fun getExtras() {
        launch = intent.extras?.getParcelable(PARAM_LAUNCH)
    }

    private fun initializeViewComponents() {
        initializeIconBack()
        initializeVideoLayout()
        initializeLaunchInfo()
    }

    private fun initializeIconBack() {
        detailsIcBack.setOnClickListener { onBackPressed() }
    }

    private fun initializeVideoLayout() {
        if (launch?.links?.youtubeId != null) {
            initializeVideoView()
        } else {
            detailsVideo.visibility = View.GONE
            launch?.setLaunchImageOrDefault(imageLoader, detailsLaunchImage)
        }
    }

    private fun initializeVideoView() {
        detailsVideo.initialize(BuildConfig.API_KEY, this)
    }

    private fun initializeLaunchInfo() {
        setMissionInfo()
        setLaunchSiteInfo()
        setRocketInfo()
    }

    private fun setMissionInfo() {
        launch?.setLaunchStatusOnTextView(detailsTextStatus)
        detailsTextName.text = launch?.missionName
        launch?.setFormattedDateOnTextView(detailsTextDate)
    }

    private fun setLaunchSiteInfo() {
        launch?.launchSite?.let { launchSite ->
            detailsTextLaunchSiteName.text = getString(
                R.string.details_launch_site_name,
                launchSite.siteName,
                launchSite.siteNameLong
            )
        }
    }

    private fun setRocketInfo() {
        launch?.rocket?.let { rocket ->
            detailsTextRocketName.text = getString(R.string.details_rocket_name, rocket.rocketName)
            detailsTextRocketType.text = getString(R.string.details_rocket_type, rocket.rocketType)
        }
    }

    companion object {
        private const val RECOVERY_REQUEST = 1

        fun start(context: Context, launch: Launch) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(PARAM_LAUNCH, launch)
            context.startActivity(intent)
        }
    }

}
