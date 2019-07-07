package com.example.test1

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mNotificationTime = Calendar.getInstance().timeInMillis + 500 //Set after 5 seconds from the current time.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        showData()

        fab.setOnClickListener { view ->
            NotificationUtils().setNotification(mNotificationTime, this@MainActivity)
            Snackbar.make(view, "Notifikasi 5 detik lagi", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        }

        btnAddToDb.setOnClickListener {
            val dbHandler = OpenHelper(this, null)
            val user = Name(etNo.text.toString())
            val date = Date(etTanggal.text.toString())
            dbHandler.addName(user, date)
            Toast.makeText(this, etNo.text.toString() + "Added to database", Toast.LENGTH_LONG).show()
            showData()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showData(){
        tvDisplayName.text = ""
        val dbHandler = OpenHelper(this, null)
        val cursor = dbHandler.getAll()
        cursor!!.moveToFirst()
        tvDisplayName.append((cursor.getString(0)))
        tvDisplayName.append("    ")
        tvDisplayName.append((cursor.getString(1)))
        tvDisplayName.append("    ")
        tvDisplayName.append((cursor.getString(2)))
        tvDisplayName.append("\n")
        // tvDisplayName.append((cursorD.getString(cursorD.getColumnIndex(OpenHelper.COLUMN_NAME_EXP))))
        while (cursor.moveToNext()) {
            tvDisplayName.append((cursor.getString(0)))
            tvDisplayName.append("    ")
            tvDisplayName.append((cursor.getString(1)))
            tvDisplayName.append("    ")
            tvDisplayName.append((cursor.getString(2)))
            // tvDisplayName.append((cursorD.getString(cursorD.getColumnIndex(OpenHelper.COLUMN_NAME_EXP))))
            tvDisplayName.append("\n")
        }
        cursor.close()
    }
}
