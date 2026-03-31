package com.wacaw.week03.adapter
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wacaw.week03.data.Product

class ProductAdapter(private var productList: List<Product>,
    private val onVisitClicked: (Product)-> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        p0: RecyclerView.ViewHolder,
        p1: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}