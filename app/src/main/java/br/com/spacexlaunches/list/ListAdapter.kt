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
import kotlinx.android.synthetic.main.list_item_launch.view.*

class ListAdapter(
    private val imageLoader: ImageLoader,
    private val launches: List<Launch>
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_launch, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = launches.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val launch = launches[position]
        bindBg(holder, launch)
        bindName(holder, launch)
        bindDate(holder, launch)
    }

    // Private methods

    private fun bindBg(holder: ListViewHolder, launch: Launch) {
        val images = launch.links.flickrImages
        if (images.isNotEmpty()) {
            imageLoader.loadImageFromUrl(images[0], holder.imageBg)
        }
    }

    private fun bindName(holder: ListViewHolder, launch: Launch) {
        holder.textName.text = launch.missionName
    }

    private fun bindDate(holder: ListViewHolder, launch: Launch) {
        holder.textDate.text = launch.launchDateUtc
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageBg: ImageView = itemView.listItemLaunchImage
        val textName: TextView = itemView.listItemLaunchTextName
        val textDate: TextView = itemView.listItemLaunchTextDate
    }

}
