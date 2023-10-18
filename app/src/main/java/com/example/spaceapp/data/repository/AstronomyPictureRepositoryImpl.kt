package com.example.spaceapp.data.repository

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.example.spaceapp.domain.model.AstronomyPictureParam
import com.example.spaceapp.domain.repository.AstronomyPictureRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL


class AstronomyPictureRepositoryImpl : AstronomyPictureRepository {
    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun loadPucture(param: AstronomyPictureParam) : Drawable? {
        val testUrl = "https://apod.nasa.gov/apod/image/2310/AnnularProposal_Zhang_960.jpg"

        var result: Drawable? = null
        val job = GlobalScope.launch {
            val connection = URL(testUrl).openConnection() as HttpURLConnection
            connection.setRequestProperty("User-agent", "Mozilla/4.0")

            connection.connect()
            val input = connection.inputStream

            val bitmap = BitmapFactory.decodeStream(input)
            result = BitmapDrawable(Resources.getSystem(), bitmap)
        }

        job.join()
        return result
    }

    override fun loadExplanation(param: AstronomyPictureParam) : String
    {
        return "Yes, but can your tree do this?  If you look closely at the ground in the " +
                "featured image, you will see many images of yesterday's solar eclipse -- " +
                "created by a tree. Gaps between tree leaves act like pinhole lenses and each " +
                "create a small image of the partially eclipsed Sun visible in the other " +
                "direction."
    }
}