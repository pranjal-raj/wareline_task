import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.io.File
import java.io.InputStream
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.URL

class ImageLoader(private val imageView: WeakReference<ImageView>,
                  private val cacheDir: File) :
    AsyncTask<String, Void, Bitmap?>() {

    override fun doInBackground(vararg params: String?): Bitmap? {
        val imageUrl = params[0]
        var bitmap: Bitmap? = null

        bitmap = imageUrl?.let { ImageCache.getBitmapFromMemoryCache(it) };
        if (bitmap != null) {
            return bitmap;
        }
        try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val inputStream: InputStream = connection.inputStream
//            bitmap = BitmapFactory.decodeStream(inputStream)


            bitmap = imageUrl?.let { ImageCache.getBitmapFromDiskCache(it, cacheDir) }
            if (bitmap == null) {
                bitmap = BitmapFactory.decodeStream(inputStream)
                // Cache downloaded image
                bitmap?.let {
                    if (imageUrl != null) {
                        ImageCache.putBitmapInMemoryCache(imageUrl, it)
                    }
                    if (imageUrl != null) {
                        ImageCache.saveBitmapToDiskCache(imageUrl, it, cacheDir)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

    override fun onPostExecute(result: Bitmap?) {
        result?.let {
            imageView.get()?.setImageBitmap(it)
            // Cache the image
            cacheImage(imageView, it)
        }
    }


    private fun cacheImage(imageView: WeakReference<ImageView>, bitmap: Bitmap) {
        val imageUrl = imageView.get()?.tag as? String
        imageUrl?.let {
            // Save the image to memory cache
            ImageCache.putBitmapInMemoryCache(imageUrl, bitmap)
            // Save the image to disk cache
            ImageCache.saveBitmapToDiskCache(imageUrl, bitmap, cacheDir)
        }
    }
}
