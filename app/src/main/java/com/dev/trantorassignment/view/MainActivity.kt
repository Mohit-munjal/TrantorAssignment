package com.dev.trantorassignment.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dev.trantorassignment.R
import com.dev.trantorassignment.databinding.ActivityMainBinding
import com.dev.trantorassignment.util.CommonUtil.loadImageFile
import com.dev.trantorassignment.util.CommonUtil.updateProgress
import com.dev.trantorassignment.util.DownloadState
import com.dev.trantorassignment.util.Mode
import com.dev.trantorassignment.viewmodel.DownloadViewModel
import java.io.File


private const val TAG = "mohit"
class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    lateinit var viewModel: DownloadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityMainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.activity=this
        init()
    }
    private fun init(){
        onSelectMode(Mode.SYNC)
        activityMainBinding.state=DownloadState.NOT_STARTED
        viewModel = ViewModelProviders.of(this).get(DownloadViewModel::class.java)
        viewModel.img1.observe(this,img1Obeserver)
        viewModel.img2.observe(this,img2Obeserver)
        viewModel.img3.observe(this,img3Obeserver)
        viewModel.img4.observe(this,img4Obeserver)
        viewModel.progress1.observe(this,progress1Observer)
        viewModel.progress2.observe(this,progress2Observer)
        viewModel.progress3.observe(this,progress3Observer)
        viewModel.progress4.observe(this,progress4Observer)
        viewModel.finished.observe(this,finishedObserver)


    }
    private val img1Obeserver=Observer<File>{ file->
        file?.let {
            loadImageFile(it,activityMainBinding.iv1)

        }
        activityMainBinding.llProgress1.visibility=View.GONE

    }
    private val img2Obeserver=Observer<File>{ file->
        file?.let {
            loadImageFile(it,activityMainBinding.iv2)

        }
        activityMainBinding.llProgress2.visibility=View.GONE

    }
    private val img3Obeserver=Observer<File>{ file->
        file?.let {
            loadImageFile(it,activityMainBinding.iv3)

        }
        activityMainBinding.llProgress3.visibility=View.GONE

    }
    private val img4Obeserver=Observer<File>{ file->
        file?.let {
            loadImageFile(it,activityMainBinding.iv4)

        }
        activityMainBinding.llProgress4.visibility=View.GONE
    }
    private val progress1Observer= Observer<Int> {progress->
        updateProgress(progress,activityMainBinding.pb1,activityMainBinding.tvProgress1)
    }
    private val progress2Observer= Observer<Int> {progress->
        updateProgress(progress,activityMainBinding.pb2,activityMainBinding.tvProgress2)
    }
    private val progress3Observer= Observer<Int> {progress->
        updateProgress(progress,activityMainBinding.pb3,activityMainBinding.tvProgress3)
    }
    private val progress4Observer= Observer<Int> {progress->
        updateProgress(progress,activityMainBinding.pb4,activityMainBinding.tvProgress4)
    }
    private val finishedObserver= Observer<Boolean> {finished->
        activityMainBinding.state=DownloadState.COMPLETED
    }

    fun onSelectMode(mode: String){
        activityMainBinding.mode=mode
    }

    fun onStartDownload(mode: String, state: String){
        if(state.equals(DownloadState.NOT_STARTED))
        {
            activityMainBinding.state=DownloadState.STARTED
            viewModel.start(mode)
        }
        else if(state.equals(DownloadState.STARTED))
        {
            activityMainBinding.state=DownloadState.PAUSED
            viewModel.pause()

        }
        else if(state.equals(DownloadState.PAUSED))
        {
            activityMainBinding.state=DownloadState.STARTED
            viewModel.resume()
        }

    }


}