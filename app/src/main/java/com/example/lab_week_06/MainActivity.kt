package com.example.lab_week_06

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    // Adapter dengan listener klik item
    private val catAdapter by lazy {
        CatAdapter(
            layoutInflater,
            GlideImageLoader(this),
            object : CatAdapter.OnClickListener {
                override fun onItemClick(cat: CatModel) {
                    showSelectionDialog(cat)
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Pasang adapter ke RecyclerView
        recyclerView.adapter = catAdapter

        // Atur layout manager (linear vertical list)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // 🔹 Swipe-to-delete
        val itemTouchHelper = ItemTouchHelper(catAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // 🔹 Tambahkan data dummy 10 item
        catAdapter.setData(
            listOf(
                CatModel(Gender.Male, CatBreed.BalineseJavanese, "Fred", "Silent and deadly", "https://cdn2.thecatapi.com/images/7dj.jpg"),
                CatModel(Gender.Female, CatBreed.ExoticShorthair, "Wilma", "Cuddly assassin", "https://cdn2.thecatapi.com/images/egv.jpg"),
                CatModel(Gender.Unknown, CatBreed.AmericanCurl, "Curious George", "Award winning investigator", "https://cdn2.thecatapi.com/images/bar.jpg"),
                CatModel(Gender.Male, CatBreed.Bengal, "Tiger", "Fast and fierce", "https://cdn2.thecatapi.com/images/O3btzLlsO.png"),
                CatModel(Gender.Female, CatBreed.Birman, "Luna", "Loves naps and snacks", "https://cdn2.thecatapi.com/images/HOrX5gwLS.jpg"),
                CatModel(Gender.Male, CatBreed.Bombay, "Shadow", "Always hides under the couch", "https://cdn2.thecatapi.com/images/5iYq9NmT1.jpg"),
                CatModel(Gender.Female, CatBreed.MaineCoon, "Misty", "Fluffy and friendly", "https://cdn2.thecatapi.com/images/du.jpg"),
                CatModel(Gender.Male, CatBreed.Persian, "Simba", "King of the living room", "https://cdn2.thecatapi.com/images/8pCFG7gCV.jpg"),
                CatModel(Gender.Unknown, CatBreed.Sphynx, "Baldy", "No fur but full of love", "https://cdn2.thecatapi.com/images/BDb8ZXb1v.jpg"),
                CatModel(Gender.Female, CatBreed.AmericanCurl, "Daisy", "Always curious", "https://cdn2.thecatapi.com/images/cj.jpg")
            )
        )
    }

    // Fungsi dialog ketika item diklik
    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            .setTitle("Cat Selected")
            .setMessage("You have selected cat ${cat.name}")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
