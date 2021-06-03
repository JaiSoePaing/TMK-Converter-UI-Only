package com.soepaing.tmkconverter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private fun copyTextToClipboard() {
        val textToCopy = editText.text
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", textToCopy)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()
    }
    private fun pasteTextFromClipboard() {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        textView.text = clipboardManager.primaryClip?.getItemAt(0)?.text
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        converter.setOnClickListener() {
            val inputValue: String = editText.text.toString()
            if (inputValue == null || inputValue.trim() == "") {
                Toast.makeText(this, "Please Write Something", Toast.LENGTH_SHORT).show()
            } else {
                textView.setText(inputValue).toString()
            }
        }

        copyAction.setOnClickListener {
            copyTextToClipboard()
        }
        pasteAction.setOnClickListener {
            pasteTextFromClipboard()
        }
    }

    fun deleteText(view: View) {
        editText.text.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.itemId

        if (id == R.id.action_settings) {
            val option = Intent(this, Option::class.java)
            startActivity(option)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
