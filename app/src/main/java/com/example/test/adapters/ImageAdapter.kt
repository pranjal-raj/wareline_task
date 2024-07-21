import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import java.io.File
import java.lang.ref.WeakReference
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var imageUrls: List<String> = listOf()
    private var cacheDir: File? = null

    fun setImageUrls(imageUrls: List<String>) {
        this.imageUrls = imageUrls
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = imageUrls[position]
        holder.bind(imageUrl)
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        private var currentImageUrl: String? = null
        private var imageLoader: ImageLoader? = null
//        val cacheDir: File? = context.externalCacheDir
        fun bind(imageUrl: String) {
            val cacheDir: File? = itemView.context.externalCacheDir
            imageLoader = cacheDir?.let { ImageLoader(WeakReference(imageView), it) }
            imageLoader?.execute(imageUrl)
        }

        fun cancelLoading() {
            imageLoader?.cancel(true)
        }

        fun getImageUrl(): String? {
            return currentImageUrl
        }
    }
}
