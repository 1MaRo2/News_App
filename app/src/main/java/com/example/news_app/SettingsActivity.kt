package com.example.news_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.news_app.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Settings"
        binding.rbGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_de -> changeCountry("de")
                R.id.rb_eg -> changeCountry("eg")
                R.id.rb_jp -> changeCountry("jp")
                R.id.rb_us -> changeCountry("us")
            }
        }

    }

    private fun changeCountry(code: String) {
        val editor = getSharedPreferences("settings", MODE_PRIVATE).edit()
        editor.putString("code", code)
        editor.apply()
    }
}