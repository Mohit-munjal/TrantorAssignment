package com.dev.trantorassignment.util

import android.app.DownloadManager
import android.content.Context
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.*


private const val TAG = "mohit"
class ImageDownloadService(var context: Context) {

//    fun downloadImage(imgFile:String){
//        val url = URL(imgFile)
//        val conection: URLConnection = url.openConnection()
//        conection.connect()
//        // getting file length
//        // getting file length
//        val lenghtOfFile: Int = conection.getContentLength()
//
//        // input stream to read file - with 8k buffer
//
//        // input stream to read file - with 8k buffer
//        val input: InputStream = BufferedInputStream(url.openStream(), 8192)
//
//        // Output stream to write file
//
//        // Output stream to write file
//        val output: OutputStream = FileOutputStream("/sdcard/downloadedfile.jpg")
//
//        val data = ByteArray(1024)
//
//        var total: Long = 0
//
//        while (input.read(data).also { count = it } !== -1) {
//            total += count
//            // publishing the progress....
//            // After this onProgressUpdate will be called
//            publishProgress("" + (total * 100 / lenghtOfFile).toInt())
//
//            // writing data to file
//            output.write(data, 0, count)
//        }
//
//        // flushing output
//
//        // flushing output
//        output.flush()
//
//        // closing streams
//
//        // closing streams
//        output.close()
//        input.close()
//    }
}