package com.example.lab2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewPlayerActivity : AppCompatActivity() {

    private lateinit var usernameView: EditText
    private lateinit var numeView: EditText
    private lateinit var emailView: EditText
    private lateinit var dataNasteriiView: EditText
    private lateinit var gradView: EditText
    private lateinit var nrMeciuriView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_player)
        usernameView = findViewById(R.id.username)
        numeView = findViewById(R.id.nume)
        emailView = findViewById(R.id.email)
        dataNasteriiView = findViewById(R.id.dataNasterii)
        gradView = findViewById(R.id.grad)
        nrMeciuriView = findViewById(R.id.nrMeciuri)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val username = usernameView.text.toString()
            val nume = numeView.text.toString()
            val email = emailView.text.toString()
            val dataNasterii = dataNasteriiView.text.toString()
            val grad = gradView.text.toString()
            val nrMeciuri = nrMeciuriView.text.toString()
            val replyIntent = Intent()
            replyIntent.putExtra("username", username)
            replyIntent.putExtra("nume", nume)
            replyIntent.putExtra("email", email)
            replyIntent.putExtra("dataNasterii", dataNasterii)
            replyIntent.putExtra("grad", grad)
            replyIntent.putExtra("nrMeciuri", nrMeciuri)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()

        }
    }
}