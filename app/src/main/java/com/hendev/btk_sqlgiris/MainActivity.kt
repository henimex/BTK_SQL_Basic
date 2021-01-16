package com.hendev.btk_sqlgiris

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val veritabani = this.openOrCreateDatabase("Urunler", MODE_PRIVATE, null)
            veritabani.execSQL("CREATE TABLE IF NOT EXISTS urunler (id INTEGER PRIMARY KEY, isim VARCHAR(15), fiyat INT)")
            //veritabani.execSQL("INSERT INTO urunler (isim, fiyat) VALUES ('Telefon', 3225)")

            veritabani.execSQL("DELETE FROM urunler WHERE id = 5")
            veritabani.execSQL("UPDATE urunler SET isim = 'Klavye', fiyat = 145 WHERE id = 3")
            veritabani.execSQL("UPDATE urunler SET isim = 'Halı', fiyat = 65 WHERE id = 4")


            val cursor = veritabani.rawQuery("SELECT * FROM urunler",null)
            //val cursor = veritabani.rawQuery("SELECT * FROM urunler WHERE isim = 'Test'",null)

            val idColumnIndex = cursor.getColumnIndex("id")
            val isimColumnIndex = cursor.getColumnIndex("isim")
            val fiyatColumnIndex = cursor.getColumnIndex("fiyat")

            while (cursor.moveToNext()){
                println("ID : ${cursor.getInt(idColumnIndex)}")
                println("İsim : ${cursor.getString(isimColumnIndex)}")
                println("Fiyat : ${cursor.getInt(fiyatColumnIndex)}")
            }
            cursor.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}