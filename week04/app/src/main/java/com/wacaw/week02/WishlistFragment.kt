package com.wacaw.week02

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wacaw.week02.adapter.NewProductAdapter
import com.wacaw.week02.adapter.ProductAdapter
import com.wacaw.week02.data.ProductData
import com.wacaw.week02.data.database.ProductDatabase
import com.wacaw.week02.data.repository.ProductRepository
import com.wacaw.week02.databinding.FragmentHomeBinding
import com.wacaw.week02.databinding.FragmentHomeBinding.inflate
import com.wacaw.week02.databinding.FragmentWishlistBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewLifecycleOwner.lifecycleScope.launch {
            val db = ProductDatabase.getInstance(requireContext())
            val repository = ProductRepository(db.productDao(), db.categoryDao())

            val products = withContext(Dispatchers.IO) {
                repository.insertData()
                repository.getAllProducts()
            }

            withContext(Dispatchers.Main) {
                Log.d("DB", "상품 목록: $products")

                val adapter = NewProductAdapter(products)
                binding.recyclerView.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}