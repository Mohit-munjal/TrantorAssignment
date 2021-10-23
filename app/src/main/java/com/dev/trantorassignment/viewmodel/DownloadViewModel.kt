package com.dev.trantorassignment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dev.trantorassignment.util.*
import com.dev.trantorassignment.util.CommonUtil.getRootDirPath
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.Status
import java.io.File


private const val TAG = "mohit"
class DownloadViewModel(var context: Application): AndroidViewModel(context) {

    var downloadIdOne:Int=0
    var downloadIdTwo:Int=0
    var downloadIdThree:Int=0
    var downloadIdFour:Int=0
    var count=0
    val IMG1="img1.png"
    val IMG2="img2.png"
    val IMG3="img3.png"
    val IMG4="img4.png"
    private var dirPath: String? = null
    init {
        dirPath = getRootDirPath(context)
        count=0
    }


    val progress1 = MutableLiveData<Int>()
    val progress2 = MutableLiveData<Int>()
    val progress3 = MutableLiveData<Int>()
    val progress4 = MutableLiveData<Int>()
    val img1=MutableLiveData<File>()
    val img2=MutableLiveData<File>()
    val img3=MutableLiveData<File>()
    val img4=MutableLiveData<File>()
    val finished=MutableLiveData<Boolean>()



    fun start(mode: String){
       if(mode.equals(Mode.SYNC))
           startSynchronously()
        else
            startAsynchronously()
    }
    fun startAsynchronously(){
        initDownloaderOne(false)
        initDownloaderTwo(false)
        initDownloaderThree(false)
        initDownloaderFour(false)
    }
    fun startSynchronously(){
      initDownloaderOne(true)
    }

    fun initDownloaderOne(sync:Boolean){
        downloadIdOne = PRDownloader.download(ImageSource.IMG1, dirPath, IMG1)
            .build()
            .setOnStartOrResumeListener {
            }
            .setOnPauseListener {
            }
            .setOnProgressListener { progress ->
                val progressPercent = progress.currentBytes * 100 / progress.totalBytes
                progress1.value=progressPercent.toInt()
            }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    val file = File(dirPath +"/"+ IMG1)
                    Log.d(TAG, "onDownloadComplete: file "+file)
                    img1.value=file
                    file.delete()
                    if(sync)
                        initDownloaderTwo(true)
                    else {
                        count++
                        if (count == 4)
                            finished.value = true
                    }


                }

                override fun onError(error: com.downloader.Error?) {
                    Log.d(TAG, "onError: " + error.toString())
                }

            })
    }
    fun initDownloaderTwo(sync:Boolean){
        downloadIdTwo = PRDownloader.download(ImageSource.IMG2, dirPath, IMG2)
            .build()
            .setOnStartOrResumeListener {
            }
            .setOnPauseListener {
            }
            .setOnProgressListener { progress ->
                val progressPercent = progress.currentBytes * 100 / progress.totalBytes
                progress2.value=progressPercent.toInt()
            }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    val file = File(dirPath +"/"+ IMG2)
                    Log.d(TAG, "onDownloadComplete: file "+file)
                    img2.value=file
                    file.delete()
                    if(sync)
                        initDownloaderThree(true)
                    else {
                        count++
                        if (count == 4)
                            finished.value = true
                    }
                }

                override fun onError(error: com.downloader.Error?) {
                    Log.d(TAG, "onError: " + error.toString())
                }

            })
    }
    fun initDownloaderThree(sync:Boolean){
        downloadIdThree = PRDownloader.download(ImageSource.IMG3, dirPath, IMG3)
            .build()
            .setOnStartOrResumeListener {
            }
            .setOnPauseListener {
            }
            .setOnProgressListener { progress ->
                val progressPercent = progress.currentBytes * 100 / progress.totalBytes
                progress3.value=progressPercent.toInt()
            }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    val file = File(dirPath +"/"+ IMG3)
                    Log.d(TAG, "onDownloadComplete: file "+file)
                    img3.value=file
                    file.delete()
                    if (sync)
                        initDownloaderFour(true)
                    else {
                        count++
                        if (count == 4)
                            finished.value = true
                    }

                }

                override fun onError(error: com.downloader.Error?) {
                    Log.d(TAG, "onError: " + error.toString())
                }

            })
    }
    fun initDownloaderFour(sync:Boolean){
        downloadIdFour = PRDownloader.download(ImageSource.IMG4, dirPath, IMG4)
            .build()
            .setOnStartOrResumeListener {
            }
            .setOnPauseListener {
            }
            .setOnProgressListener { progress ->
                val progressPercent = progress.currentBytes * 100 / progress.totalBytes
                progress4.value=progressPercent.toInt()
            }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    val file = File(dirPath +"/"+ IMG4)
                    Log.d(TAG, "onDownloadComplete: file "+file)
                    img4.value=file
                    file.delete()
                    if(sync)
                        finished.value=true
                    else {
                        count++
                        if (count == 4)
                            finished.value = true
                    }

                }

                override fun onError(error: com.downloader.Error?) {
                    Log.d(TAG, "onError: " + error.toString())
                }

            })
    }

    fun pause(){
        if (Status.RUNNING == PRDownloader.getStatus(downloadIdOne)) {
            PRDownloader.pause(downloadIdOne)
        }
        if (Status.RUNNING == PRDownloader.getStatus(downloadIdTwo)) {
            PRDownloader.pause(downloadIdTwo)
        }
        if (Status.RUNNING == PRDownloader.getStatus(downloadIdThree)) {
            PRDownloader.pause(downloadIdThree)
        }
        if (Status.RUNNING == PRDownloader.getStatus(downloadIdFour)) {
            PRDownloader.pause(downloadIdFour)
        }
    }
    fun resume(){
        if (Status.PAUSED == PRDownloader.getStatus(downloadIdOne)) {
            PRDownloader.resume(downloadIdOne)
        }
        if (Status.PAUSED == PRDownloader.getStatus(downloadIdTwo)) {
            PRDownloader.resume(downloadIdTwo)
        }
        if (Status.PAUSED == PRDownloader.getStatus(downloadIdThree)) {
            PRDownloader.resume(downloadIdThree)
        }
        if (Status.PAUSED == PRDownloader.getStatus(downloadIdFour)) {
            PRDownloader.resume(downloadIdFour)
        }
    }


}