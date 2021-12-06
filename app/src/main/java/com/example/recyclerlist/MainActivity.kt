package com.example.recyclerlist

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        val jsonList: List<File>
        val MY_READ_EXTERNAL_REQUEST = 1
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1);
        }
        Log.i("checkPermisiion", "granted")
//        val path: String = Environment.getExternalStorageDirectory().toString()+"/Downloads"


        val path = Environment.getExternalStorageDirectory().absolutePath

        Log.d("Files", "Path: $path")
        val f = File(path)
        val file = f.listFiles()
        val data = ArrayList<ItemsViewModel>()
        Log.d("Files", "Size: " + file.size)
        for (i in file.indices) {
            Log.d("Files", "FileName:" + file[i].name)
            data.add(ItemsViewModel(R.drawable.ic_baseline_folder_24,file[i].name ))
        }
        val adapter = CustomAdapter(data)
        recyclerview.adapter = adapter
    }
}

