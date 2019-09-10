package br.com.spacexlaunches.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.spacexlaunches.R
import br.com.spacexlaunches.base.api.models.Launch
import br.com.spacexlaunches.util.ImageLoader
import br.com.spacexlaunches.util.setFormattedDateOnTextView
import br.com.spacexlaunches.util.setLaunchStatusOnTextView
import kotlinx.android.synthetic.main.list_item_launch.view.*

class ListAdapter(
    private val listener: Listener,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

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
        bindClickListener(holder, launch)
        bindBg(holder, launch)
        bindStatus(holder, launch)
        bindName(holder, launch)
        bindDate(holder, launch)
    }

    // Private methods

    private fun bindClickListener(holder: ListViewHolder, launch: Launch) {
        holder.root.setOnClickListener {
            listener.onLaunchClicked(launch)
        }
    }

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
        launch.setLaunchStatusOnTextView(holder.textStatus)
    }

    private fun bindName(holder: ListViewHolder, launch: Launch) {
        holder.textName.text = launch.missionName
    }

    private fun bindDate(holder: ListViewHolder, launch: Launch) {
        launch.setFormattedDateOnTextView(holder.textDate)
    }

    interface Listener {
        fun onLaunchClicked(launch: Launch)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val root: View = itemView
        val imageBg: ImageView = itemView.listItemLaunchImage
        val textStatus: TextView = itemView.listItemLaunchTextStatus
        val textName: TextView = itemView.listItemLaunchTextName
        val textDate: TextView = itemView.listItemLaunchTextDate
    }

}
