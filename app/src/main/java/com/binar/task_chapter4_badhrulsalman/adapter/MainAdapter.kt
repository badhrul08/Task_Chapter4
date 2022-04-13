package com.binar.task_chapter4_badhrulsalman.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.binar.task_chapter4_badhrulsalman.FirstFragmentDirections
import com.binar.task_chapter4_badhrulsalman.data.models.GetAllCarResponseItem
import com.binar.task_chapter4_badhrulsalman.databinding.ItemContentBinding

class MainAdapter (private val onItemClick: OnClickListener): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<GetAllCarResponseItem>() {
        override fun areItemsTheSame(
            oldItem: GetAllCarResponseItem,
            newItem: GetAllCarResponseItem
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: GetAllCarResponseItem,
            newItem: GetAllCarResponseItem
        ): Boolean = oldItem.hashCode() == newItem.hashCode()
    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    fun submitData(value: List<GetAllCarResponseItem>?) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemContentBinding.inflate(inflater, null, false))
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let{holder.bind(data)}
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemContentBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: GetAllCarResponseItem){
            binding.apply {
                idTv.text = data.id.toString()
                titleTv.text = data.name
                priceTv.text = data.price.toString()

                root.setOnClickListener {
                    onItemClick.onClickItem(data)
                }
                nextBtn.setOnClickListener {
                    val cars = GetAllCarResponseItem(
                        id = data.id,
                        name = data.name,
                        price = data.price
                    )
                    it.findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(cars))
                }
            }
        }
    }
    interface OnClickListener {
        fun onClickItem(data: GetAllCarResponseItem)
    }
}

