package br.com.spacexlaunches.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import br.com.spacexlaunches.R
import br.com.spacexlaunches.base.api.models.Launch
import br.com.spacexlaunches.util.DateUtil
import br.com.spacexlaunches.util.ImageLoader
import kotlinx.android.synthetic.main.list_item_launch.view.*
import java.lang.ref.WeakReference

class ListAdapter(
    context: Context?,
    private val imageLoader: ImageLoader,
    private val dateUtil: DateUtil
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var context: WeakReference<Context?> = WeakReference(context)
    private var launches: MutableList<Launch> = mutableListOf()

    // Public methods

    fun updateAdapter(launches: List<Launch>) {
        this.launches.clear()
        this.launches.addAll(launches)
        notifyDataSetChanged()
    }

    fun appendToList(newLaunches: List<Launch>) {
        this.launches.addAll(0, newLaunches)
    }

    // RecyclerView.Adapter overrides

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_launch, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = launches.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val launch = launches[position]
        bindBg(holder, launch)
        bindStatus(holder, launch)
        bindName(holder, launch)
        bindDate(holder, launch)
    }

    // Private methods

    private fun bindBg(holder: ListViewHolder, launch: Launch) {
        val images = launch.links?.flickrImages
        if (images.isNullOrEmpty().not() && images?.get(0).isNullOrBlank().not()) {
            images?.get(0)?.let { firstImageUrl ->
                imageLoader.loadImageFromUrl(firstImageUrl, holder.imageBg, R.drawable.bg_rocket)
            }
        } else {
            imageLoader.loadImageFromResource(R.drawable.bg_rocket, holder.imageBg)
        }
    }

    private fun bindStatus(holder: ListViewHolder, launch: Launch) {
        when {
            launch.upcoming == true -> setStatus(
                holder,
                R.string.list_item_launch_status_upcoming,
                R.drawable.bg_upcoming
            )
            launch.launchSuccess == true -> setStatus(
                holder,
                R.string.list_item_launch_status_success,
                R.drawable.bg_success
            )
            launch.launchSuccess == false -> setStatus(
                holder,
                R.string.list_item_launch_status_failed,
                R.drawable.bg_failed
            )
        }
    }

    private fun setStatus(holder: ListViewHolder, @StringRes statusRes: Int, @DrawableRes statusDrawable: Int) {
        holder.textStatus.text =
            context.get()?.getString(statusRes)
        holder.textStatus.setBackgroundResource(statusDrawable)
    }

    private fun bindName(holder: ListViewHolder, launch: Launch) {
        holder.textName.text = launch.missionName
    }

    private fun bindDate(holder: ListViewHolder, launch: Launch) {
        holder.textDate.text = dateUtil.parseDate(launch.launchDateUtc)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageBg: ImageView = itemView.listItemLaunchImage
        val textStatus: TextView = itemView.listItemLaunchTextStatus
        val textName: TextView = itemView.listItemLaunchTextName
        val textDate: TextView = itemView.listItemLaunchTextDate
    }

}
