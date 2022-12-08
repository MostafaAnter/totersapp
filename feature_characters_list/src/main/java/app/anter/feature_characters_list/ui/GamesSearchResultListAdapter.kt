package app.anter.feature_characters_list.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.anter.core_data.remote.responses.charactersResponse.HeroObject
import app.anter.feature_characters_list.R
import app.anter.feature_characters_list.databinding.ItemCharacterListBinding
import java.util.*

/**
 * A custom adapter to use with the RecyclerView widget.
 */
class GamesSearchResultListAdapter(
    private val mContext: Context,
    private var modelList: ArrayList<HeroObject>
) : PagingDataAdapter<HeroObject, RecyclerView.ViewHolder>(difCallBack) {


    companion object{
        private val difCallBack = object : DiffUtil.ItemCallback<HeroObject>() {
            override fun areItemsTheSame(oldItem: HeroObject, newItem: HeroObject): Boolean =
                // User ID serves as unique ID
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: HeroObject, newItem: HeroObject): Boolean =
                // Compare full contents (note: Java users should call .equals())
                oldItem.equals( newItem)
        }

    }

    // call back methods for handle click listeners
    private var mItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCharacterListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.item_character_list,
                viewGroup,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        //Here you can fill your row view
        if (holder is ViewHolder) {
            val model = getItem(position)
            holder.bind(model)
        }
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int, model: HeroObject?)
    }

    inner class ViewHolder(private val binding: ItemCharacterListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            // handle item click
            binding.root.setOnClickListener {
                mItemClickListener!!.onItemClick(
                    binding.root,
                    bindingAdapterPosition,
                    getItem(bindingAdapterPosition)
                )
            }
        }

        fun bind(model: HeroObject?) {
            binding.model = model
            binding.executePendingBindings()

        }
    }
}