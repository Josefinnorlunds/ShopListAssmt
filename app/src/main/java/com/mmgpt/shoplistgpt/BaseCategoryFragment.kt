package com.mmgpt.shoplistgpt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mmgpt.shoplistgpt.databinding.FragmentCategoryBinding

abstract class BaseCategoryFragment : Fragment() {

    protected val viewModel: ShoppingListViewModel by activityViewModels()
    protected var index: Int = 0
    private var _binding: FragmentCategoryBinding? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        index = arguments?.getInt(ARG_INDEX) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_INDEX = "index"
    }
}
