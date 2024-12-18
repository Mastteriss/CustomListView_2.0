package com.example.customlistview

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var productImageView: ImageView
    private lateinit var productNameTV: TextView
    private lateinit var priceTV: TextView
    private lateinit var descriptionTV: TextView
    private lateinit var menuButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)


        productImageView = findViewById(R.id.productImageView)
        productNameTV = findViewById(R.id.productNameTV)
        priceTV = findViewById(R.id.priceTV)
        descriptionTV = findViewById(R.id.descriptionTV)
        menuButton = findViewById(R.id.menuButton)


        val productName = intent.getStringExtra("product_name") ?: ""
        val productPrice = intent.getStringExtra("product_price") ?: ""
        val productDescription = intent.getStringExtra("product_description") ?: ""
        val productImage = intent.getParcelableExtra<Bitmap>("product_image")


        productNameTV.text = productName
        priceTV.text = productPrice
        descriptionTV.text = productDescription
        productImageView.setImageBitmap(productImage)


        menuButton.setOnClickListener { finish() }
    }
}