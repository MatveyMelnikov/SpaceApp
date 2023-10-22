package com.example.spaceapp.data.storage

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import com.example.spaceapp.R
import com.example.spaceapp.data.storage.model.AstronomyPictureData
import com.example.spaceapp.data.storage.model.AstronomyPictureDataParam
import com.example.spaceapp.data.storage.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

const val PICTURE_TAG = "NASAAstronomyPictureStorage"

class NASAAstronomyPictureStorage(
    private val context: Context
) : AstronomyPictureStorage {
    override suspend fun load(param: AstronomyPictureDataParam): AstronomyPictureData? {
        val response = try {
            RetrofitInstance.astronomyPictureApi.getPicture(context.getString(R.string.api_key))
        } catch (e: IOException) {
            Log.e(PICTURE_TAG, "IOException, you might not have internet connection")
            return null
        } catch (e: HttpException) {
            Log.e(PICTURE_TAG, "HttpException, unexpected response")
            return null
        }

        if (!response.isSuccessful || response.body() == null)
            return null

        val drawable = try {
            withContext(Dispatchers.IO) {
                val connection = URL(response.body()!!.url).openConnection() as HttpURLConnection
                connection.setRequestProperty("User-agent", "Mozilla/4.0")

                connection.connect()
                val input = connection.inputStream

                val bitmap = BitmapFactory.decodeStream(input)
                BitmapDrawable(Resources.getSystem(), bitmap)
            }
        } catch (e: Exception) {
            Log.e(PICTURE_TAG, "Exception, ${e.message}")
            return null
        }

        return AstronomyPictureData(
            drawable,
            response.body()!!.explanation,
            response.body()!!.title
        )
    }
}