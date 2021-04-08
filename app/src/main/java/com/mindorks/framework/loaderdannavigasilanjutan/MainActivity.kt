package com.mindorks.framework.loaderdannavigasilanjutan

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showDetail.setOnClickListener {
            var myDetailIntent =
                Intent(this, DetailActivity::class.java)
            startActivity(myDetailIntent)
        }
        doAsync {
            Thread.sleep(5000L)
            uiThread {
                showNotivy()
            }
        }
    }

    private fun showNotivy() {
        val Channel_id = "my_channel_01"
        val name = "ON/OFF"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val nNotifyChannel = NotificationChannel(Channel_id,
            name,
            importance)
        val notfyDetailIntent = Intent(this@MainActivity, DetailActivity::class.java)
            .apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
//        val myPendingIntent = TaskStackBuilder.create(this)
//            .addNextIntentWithParentStack(notfyDetailIntent)
//            .getPendingIntent(110, PendingIntent.FLAG_UPDATE_CURRENT)

        val myPendingIntent = PendingIntent.getActivity(this,0,
            notfyDetailIntent,
            PendingIntent.FLAG_CANCEL_CURRENT);

        val myNotfyManager =
            this.getSystemService(android.content.Context.NOTIFICATION_SERVICE) as NotificationManager
        val myBuilder = NotificationCompat.Builder(this,Channel_id)
            .setContentTitle("Show Detail Contact")
            .setContentText("Klik Me")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(myPendingIntent)
            .setAutoCancel(true)
        myNotfyManager.createNotificationChannel(nNotifyChannel)
        myNotfyManager.notify(1101, myBuilder.build())
    }
}