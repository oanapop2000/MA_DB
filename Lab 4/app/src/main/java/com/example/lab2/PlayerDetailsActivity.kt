package com.example.lab2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class PlayerDetailsActivity: AppCompatActivity()  {

    private lateinit var usernameView: EditText
    private lateinit var numeView: EditText
    private lateinit var emailView: EditText
    private lateinit var dataNasteriiView: EditText
    private lateinit var gradView: EditText
    private lateinit var nrMeciuriView: EditText


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)
        usernameView = findViewById(R.id.username)
        numeView = findViewById(R.id.nume)
        emailView = findViewById(R.id.email)
        dataNasteriiView = findViewById(R.id.dataNasterii)
        gradView = findViewById(R.id.grad)
        nrMeciuriView = findViewById(R.id.nrMeciuri)

        val buttonModify = findViewById<Button>(R.id.button_modify)
        val buttonDelete = findViewById<Button>(R.id.button_delete)

        val id = intent.getStringExtra("id")
        usernameView.setText(intent.getStringExtra("username"))
        numeView.setText(intent.getStringExtra("nume"))
        emailView.setText(intent.getStringExtra("email"))
        dataNasteriiView.setText(intent.getStringExtra("dataNasterii"))
        gradView.setText(intent.getStringExtra("grad"))
        nrMeciuriView.setText(intent.getStringExtra("nrMeciuri"))

        buttonModify.setOnClickListener {
            val username = usernameView.text.toString()
            val nume = numeView.text.toString()
            val email = emailView.text.toString()
            val dataNasterii = dataNasteriiView.text.toString()
            val grad = gradView.text.toString()
            val nrMeciuri = nrMeciuriView.text.toString()
            val replyIntent = Intent()
            replyIntent.putExtra("id", id)
            replyIntent.putExtra("username", username)
            replyIntent.putExtra("nume", nume)
            replyIntent.putExtra("email", email)
            replyIntent.putExtra("dataNasterii", dataNasterii)
            replyIntent.putExtra("grad", grad)
            replyIntent.putExtra("nrMeciuri", nrMeciuri)
            replyIntent.putExtra("modificare/stergere", "modificare")
            setResult(Activity.RESULT_OK, replyIntent)
            finish()


        }

        buttonDelete.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra("id", id)
            replyIntent.putExtra("modificare/stergere", "stergere")
            setResult(Activity.RESULT_OK, replyIntent)
            finish()

        }
    }
}