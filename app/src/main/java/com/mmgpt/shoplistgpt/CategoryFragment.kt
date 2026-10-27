package com.mmgpt.shoplistgpt

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmgpt.shoplistgpt.adapter.ItemAdapter

class CategoryFragment : BaseCategoryFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ItemAdapter(onAction = { item, action ->
            when (action) {
                ItemAdapter.Action.CHECK -> viewModel.checkItem(item)
                ItemAdapter.Action.FLAG -> viewModel.flagItem(item)
                // EDIT and DELETE actions are not applicable in this fragment
                ItemAdapter.Action.EDIT, ItemAdapter.Action.DELETE -> {}
            }
        })
        binding.recyclerView.adapter = adapter

        viewModel.categories.observe(viewLifecycleOwner) { list ->
            val cat = list.getOrNull(index)
            if (cat != null) {
                binding.tvHeading.text = cat.heading
                binding.tvCount.text = "${cat.remainingCount}/${cat.totalCount}"
                adapter.submitList(cat.items)
            }
        }
    }

    companion object {
        fun newInstance(index: Int): CategoryFragment {
            val fragment = CategoryFragment()
            fragment.arguments = Bundle().apply { putInt(ARG_INDEX, index) }
            return fragment
        }
    }
}
