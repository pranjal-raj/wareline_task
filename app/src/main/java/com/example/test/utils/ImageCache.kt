import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.LruCache
import java.io.File
import java.io.FileOutputStream

object ImageCache {
    private val memoryCache: LruCache<String, Bitmap> = LruCache(1024 * 1024 * 10) // 10MB
    private const val CACHE_DIR_NAME = "image_cache"

    fun getBitmapFromMemoryCache(key: String): Bitmap? {
        return memoryCache.get(key)
    }

    fun putBitmapInMemoryCache(key: String, bitmap: Bitmap) {
        memoryCache.put(key, bitmap)
    }

    fun saveBitmapToDiskCache(key: String, bitmap: Bitmap, cacheDir: File) {
        val cacheDirFile = File(cacheDir, CACHE_DIR_NAME)
        if (!cacheDirFile.exists()) {
            cacheDirFile.mkdirs()
        }

        val file = File(cacheDirFile, key)
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
    }

    fun getBitmapFromDiskCache(key: String, cacheDir: File): Bitmap? {
        val file = File(cacheDir, CACHE_DIR_NAME + File.separator + key)
        if (file.exists()) {
            return BitmapFactory.decodeFile(file.absolutePath)
        }
        return null
    }
}
