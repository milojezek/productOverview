package eu.milo4apps.notinotask

import android.graphics.drawable.Drawable
import android.media.Image
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import coil.load
import eu.milo4apps.notinotask.network.Product
import eu.milo4apps.notinotask.overview.ProductApiStatus
import eu.milo4apps.notinotask.overview.ProductGridAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        // Load the image in the background using Coil
        val prefix = "https://i.notino.com/detail_zoom/"
        val imgUrlFormatted = prefix + imgUrl
        val imgUri = imgUrlFormatted.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Product>?) {
    val adapter = recyclerView.adapter as ProductGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("productApiStatus")
fun bindStatus(statusImageView: ImageView, status: ProductApiStatus) {
    when (status) {
        ProductApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ProductApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ProductApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> statusImageView.visibility = View.INVISIBLE
    }
}

@BindingAdapter("android:rating")
fun bindRating(ratingBar: RatingBar, score: String?) {
    ratingBar.rating = score?.toInt()!!.toFloat()
}
