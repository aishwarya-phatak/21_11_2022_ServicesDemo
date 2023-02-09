package com.example.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.services.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("file_path")){
            Toast.makeText(this,"${intent.getStringExtra("file_path")}",Toast.LENGTH_LONG).show()
        }

        binding.btnStartService.setOnClickListener {
            var intent = Intent(this,MediaPlayerService::class.java)
            intent.putExtra("file_path",binding.edtPath.text.toString())
            startService(intent)
        }

        binding.btnStopService.setOnClickListener {
            var intent = Intent(this,MediaPlayerService::class.java)
            stopService(intent)
        }

        binding.btnStartActivity.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            //intent.putExtra("file_path",binding.edtPath.text.toString())
            startActivity(intent)
        }
    }
}