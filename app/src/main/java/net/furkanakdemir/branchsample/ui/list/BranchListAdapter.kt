package net.furkanakdemir.branchsample.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.furkanakdemir.branchsample.R
import net.furkanakdemir.branchsample.image.ImageLoader
import net.furkanakdemir.branchsample.ui.BranchViewItem
import net.furkanakdemir.branchsample.ui.base.BaseViewHolder

class BranchListAdapter(val imageLoader: ImageLoader, val onBranchClick: (BranchViewItem) -> Unit) :
    RecyclerView.Adapter<BaseViewHolder<BranchViewItem>>() {


    var branches: MutableList<BranchViewItem> = mutableListOf()
        set(value) {
            branches.clear()
            branches.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BranchViewItem> {

        return BranchViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_branch, parent, false)
        )
    }

    override fun getItemCount() = branches.size

    override fun onBindViewHolder(holder: BaseViewHolder<BranchViewItem>, position: Int) {
        val viewItem = branches[position]

        holder.bind(viewItem)
    }

    inner class BranchViewHolder(itemView: View) :
        BaseViewHolder<BranchViewItem>(itemView) {
        override fun bind(item: BranchViewItem) {

            itemView.setOnClickListener {
                onBranchClick(item)
            }
        }
    }
}
