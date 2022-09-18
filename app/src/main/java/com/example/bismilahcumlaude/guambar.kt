package com.example.bismilahcumlaude

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import Database.GlobalVar
import Model.User
import androidx.activity.result.contract.ActivityResultContracts
import com.example.bismilahcumlaude.databinding.ActivityAddathBinding

class guambar : AppCompatActivity() {
    private lateinit var viewBind: ActivityAddathBinding
    private lateinit var movie: User
    var position = -1
    var image: String = ""

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            val uri = it.data?.data
            if(uri != null){
                baseContext.getContentResolver().takePersistableUriPermission(uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            }
            viewBind.imageView2.setImageURI(uri)
            image = uri.toString()
        }
    }


    private fun getintent(){
        position = intent.getIntExtra("position", -1)
        if(position != -1){
            val movie = GlobalVar.listi[position]
            viewBind.toolbar2.title = "Edit Hewan"
            viewBind.movieAdd.text = "Edit"
            viewBind.imageView2.setImageURI(Uri.parse(GlobalVar.listi[position].imageUri))
            viewBind.Rating.editText?.setText(movie.type.toString())
            viewBind.Title.editText?.setText(movie.thename)
            viewBind.Genre.editText?.setText(movie.umr)
        }
    }

    private fun listener(){
        viewBind.imageView2.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBind.movieAdd.setOnClickListener{
            var nama = viewBind.Title.editText?.text.toString().trim()
            var ihik = viewBind.Rating.editText?.text.toString().trim()
            var ahek = viewBind.Genre.editText?.text.toString().trim()

            movie = User(nama, ihik, ahek)
            checker()
        }

        viewBind.toolbar2.getChildAt(1).setOnClickListener {
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityAddathBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportActionBar?.hide()
        getintent()
        listener()
    }

    private fun checker()
    {
        var isCompleted:Boolean = true

        if(movie.thename!!.isEmpty()){
            viewBind.Title.error = "this cannot be empty"
            isCompleted = false
        }else{
            viewBind.Title.error = ""
        }

        if(movie.umr!!.isEmpty()){
            viewBind.Genre.error = "this cannot be empty"
            isCompleted = false
        }else{
            viewBind.Genre.error = ""
        }




        movie.imageUri = image

        if(viewBind.Rating.editText?.text.toString().isEmpty() || viewBind.Rating.editText?.text.toString().toInt() < 0)
        {
            viewBind.Rating.error = "this cannot be empty"
            isCompleted = false
        }

        if(isCompleted == true)
        {
            if(position == -1)
            {
                movie.type
                viewBind.Rating.editText?.text.toString()
                GlobalVar.listi.add(movie)

            }else
            {
                movie.type = viewBind.Rating.editText?.text.toString()
                GlobalVar.listi[position] = movie
            }
            finish()
        }
    }
}