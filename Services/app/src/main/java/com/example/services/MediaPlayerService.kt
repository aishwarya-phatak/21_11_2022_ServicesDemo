package com.example.services

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Environment
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import java.net.URI
import java.net.URL

class MediaPlayerService : Service(){
    private lateinit var mediaPlayer: MediaPlayer
    lateinit var uri: Uri
    lateinit var url: URL

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this,"${this.javaClass.name} : onCreate", Toast.LENGTH_LONG).show()
        Log.e("tag","onCreate  Method of Service Called")
        /*uri = Uri.parse(Environment.getExternalStorageDirectory().path +"/Music/audio_file.mp3")
        Log.e("tag","$uri -- ${uri.path} -- ${uri.encodedPath} -- ${Environment.getExternalStorageDirectory().path}")*/
    }
    // file:///storage/emulated/0/Music/15.mp4
    // file:///storage/emulated/0/Music/audio_file.mp3
    // ---> /storage/emulated/0/Music/audio_file.mp3

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("tag","onStartCommand of Service Called")

        if (intent != null) {
            var path = intent.getStringExtra("file_path")
            uri = Uri.parse(path)
            Toast.makeText(this,"$startId -- ${intent.getStringExtra("file_path")}", Toast.LENGTH_LONG).show()
        }

        //uri = Uri.parse("file:///storage/emulated/0/Music/Chaand Taare Yes Boss 320 Kbps.mp3")

        //uri = Uri.parse(Environment.getExternalStorageDirectory().path+"/Music/Chaand Taare Yes Boss 320 Kbps.mp3") --> 2
        // mediaPlayer = MediaPlayer.create(this,R.raw.audio_file) --> 1
       /* url = URL("https://www.jiosaavn.com/song/jai-jai-ram/BiE5fBVxf0s")
        uri = url.toURI() as Uri
        Log.e("tag","$uri -- $url")*/
        mediaPlayer = MediaPlayer.create(this,uri)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("tag","onDestroyCalled of Service Called")
        Toast.makeText(this,"this class ${this.javaClass.name} : onDestroy()",Toast.LENGTH_LONG).show()
        mediaPlayer.stop()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e("tag","onBind")
        return null
    }

    /*override fun onCompletion(mp: MediaPlayer?) {
        stopSelf()
    }*/
}