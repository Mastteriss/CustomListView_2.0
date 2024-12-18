package com.example.customlistview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity(),Removable,Updateble {
    val shop:Shop? = null
    var listAdapter:ListAdapter? = null
    private val GALLERY_REQUEST = 302
    private lateinit var menuButton:ImageButton
    var photoUri:Uri? = null
    val shops:MutableList<Shop> = mutableListOf()
    var item:Int? = null
    var check = true



    private lateinit var listViewLV:ListView
    private lateinit var productNameET:EditText
    private lateinit var priceET:EditText
    private lateinit var editImageIV:ImageView
    private lateinit var addBTN:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        init()
        menuButton = findViewById(R.id.menuButton)
        menuButton.setOnClickListener{
            shopMenu(it)
        }


        editImageIV.setOnClickListener{
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent,GALLERY_REQUEST)
        }

        addBTN.setOnClickListener {
            createShop()

            listAdapter = ListAdapter(this@SecondActivity, shops)
            listViewLV.adapter = listAdapter
            listAdapter?.notifyDataSetChanged()
            clearEditFields()
            listAdapter?.notifyDataSetChanged()
        }
        listViewLV.onItemClickListener =
            AdapterView.OnItemClickListener{parent, view, position, id ->
                val shop = listAdapter!!.getItem(position)
                item = position
                val dialog = MyAlertDialog()
                val args = Bundle()
                args.putSerializable("shop", shop)
                dialog.arguments = args
                dialog.show(supportFragmentManager, "custom")
            }

    }
    private fun shopMenu(view: View){
        val popupMenu = PopupMenu(this,view)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item:MenuItem ->
            when(item.itemId){
                R.id.exit ->{
                    finish()
                    true
                }
                else ->false
            }
        }
        popupMenu.show()
    }

    private fun clearEditFields() {
        productNameET.text.clear()
        priceET.text.clear()
        editImageIV.setImageResource(R.drawable.baseline_add_photo_alternate_24)
    }

    private fun createShop() {
        val shopName = productNameET.text.toString()
        val shopPrice = priceET.text.toString()
        val shopImage = photoUri.toString()

        val shop = Shop(shopName, shopPrice, shopImage)
        shops.add(shop!!)
        clearEditFields()
        photoUri = null
    }

    private fun init() {
        listViewLV = findViewById(R.id.listViewLV)
        productNameET = findViewById(R.id.productNameET)
        priceET = findViewById(R.id.priceET)
        editImageIV = findViewById(R.id.editImageIV)
        addBTN = findViewById(R.id.addBTN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        editImageIV = findViewById(R.id.editImageIV)
        when(requestCode){
            GALLERY_REQUEST -> if(resultCode === RESULT_OK){

               photoUri = data?.data
                editImageIV.setImageURI(photoUri)
            }
        }
    }

    override fun remove(shop: Shop) {
        listAdapter?.remove(shop)
    }

    override fun update(shop: Shop) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("position", item)
        intent.putExtra("check", check)
        intent.putExtra("shops",this.shops as ArrayList<Shop>)
        intent.putExtra("shop", shop)
        intent.putExtra("product", shop.product)
        intent.putExtra("price", shop.price)
        intent.putExtra("image",shop.image)
        startActivity(intent)
        finish()
    }
}