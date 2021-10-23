package com.dev.trantorassignment.util

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Environment
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.io.File


object CommonUtil{

    fun getRootDirPath(context: Context): String? {
        return if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            val file = ContextCompat.getExternalFilesDirs(
                context.getApplicationContext(),
                null
            )[0]
            file.absolutePath
        } else {
            context.getApplicationContext().getFilesDir().getAbsolutePath()
        }
    }

    fun loadImageFile(file: File, imageView: ImageView){

                file?.let {
                    if (it.exists()) {
                        val myBitmap = BitmapFactory.decodeFile(it.getAbsolutePath())
                        imageView.setImageBitmap(myBitmap)
                    }
                }

                file?.delete()
    }
     fun updateProgress(progress: Int, progressBar: ProgressBar, textView: TextView){
        progressBar.progress=progress
        textView.text="$progress%"

    }
}
