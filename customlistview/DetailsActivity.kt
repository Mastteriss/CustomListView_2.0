package com.example.customlistview

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    private lateinit var exitBTN:Button
    private lateinit var productTV:TextView
    private lateinit var priceTV:TextView
    private lateinit var editImageTV:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)
        productTV = findViewById(R.id.productTV)
        exitBTN = findViewById(R.id.exitBTN)
        priceTV = findViewById(R.id.priceTV)
        editImageTV = findViewById(R.id.editImageIV)
        val product = intent.getStringExtra("product")
        val price  = intent.getStringExtra("price")
        val image = intent.getStringExtra("image")
        exitBTN.setOnClickListener { finish() }
        productTV.text = product?:"Продукт не указан"
        priceTV.text = price?:"Цена не указана"
        if (image != null) {
            editImageTV.setImageURI(Uri.parse(image))
        } else {
            editImageTV.setImageResource(R.drawable.ic_menu)
        }

    }
}