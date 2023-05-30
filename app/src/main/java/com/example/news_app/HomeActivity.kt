package com.example.news_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.news_app.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Categoreis"
        binding.scienceBtn.setOnClickListener { sendCat("science") }
        binding.techBtn.setOnClickListener { sendCat("technology") }
        binding.sportsBtn.setOnClickListener { sendCat("sports") }
        binding.businessBtn.setOnClickListener { sendCat("business") }
        binding.entBtn.setOnClickListener { sendCat("entertainment") }
        binding.generalBtn.setOnClickListener { sendCat("general") }
    }

    fun sendCat(cat: String) {
        val i = Intent(this, MainActivity::class.java)
        i.putExtra("cat", cat)
        startActivity(i)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings_item) {
            val i = Intent(this, SettingsActivity::class.java)
            startActivity(i)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}