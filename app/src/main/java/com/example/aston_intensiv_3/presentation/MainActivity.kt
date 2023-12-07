package com.example.aston_intensiv_3.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.aston_intensiv_3.R
import com.example.aston_intensiv_3.presentation.contacts_fragment.ContactsViewModel
import com.example.aston_intensiv_3.presentation.contacts_fragment.StartFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var sharedViewModel: ContactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = ViewModelProvider(this)[ContactsViewModel::class.java]

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, StartFragment(), null).commit()
        }
    }

    fun getSharedViewModel(): ContactsViewModel {
        return sharedViewModel
    }
}
