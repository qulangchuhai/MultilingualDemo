package com.test.app

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.test.app.internationalization.R
import com.test.app.internationalization.databinding.ActivityMainLayoutBinding


class MainActivity : BaseForLanguageActivity<ActivityMainLayoutBinding>() {
    val READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE"
    val POST_NOTIFICATIONS = "android.permission.POST_NOTIFICATIONS"

    val channelId = "1"
    val channelName = "test"
    var notificationId = 1
    val REQUESTCODE_REQUEST_READ_PERMISSION = 0
    var REQUESTCODE_REQUEST_NOTIFICATION_PERMISSION = 1


    override fun getlayoutId(): Int {
        return R.layout.activity_main_layout
    }

    override fun initView() {


        bindingView?.changeLanguageId?.setOnClickListener {
            startActivity(Intent(this@MainActivity, ChangeLanguageActivity::class.java))
        }
        bindingView?.requestReadPermissionId?.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(READ_EXTERNAL_STORAGE),
                        REQUESTCODE_REQUEST_READ_PERMISSION
                    )
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        Extentions.getString(R.string.app_have_read_permission),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@MainActivity,
                    Extentions.getString(R.string.app_have_read_permission),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        bindingView?.openServiceId?.setOnClickListener {
            val startIntent = Intent(this@MainActivity, TestService::class.java)
            startService(startIntent)// 通过 startService() 方法启动 MyService 服务
        }
        bindingView?.sendNotificationId?.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ContextCompat.checkSelfPermission(
                    this@MainActivity, POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_DENIED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(POST_NOTIFICATIONS),
                    REQUESTCODE_REQUEST_NOTIFICATION_PERMISSION
                )
            } else {
                sendNotification()
            }


        }
    }

    private fun sendNotification() {
        val manager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: Notification.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            channel.setShowBadge(true)
            manager.createNotificationChannel(channel)
            builder = Notification.Builder(this, channelId)
            channel.enableVibration(true)
            channel.enableLights(true)
        } else {
            builder = Notification.Builder(this)
            builder.setDefaults(Notification.DEFAULT_SOUND)
            builder.setLights(-0x100, 0, 2000)
            builder.setVibrate(longArrayOf(0, 100, 300))
        }
        builder.setLargeIcon(
            BitmapFactory.decodeResource(
                resources,
                R.mipmap.ic_launcher
            )
        )
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setContentTitle(Extentions.getString(R.string.app_notification_title))
        builder.setContentText(Extentions.getString(R.string.app_notification_content))
        builder.setAutoCancel(true)
        val baseNF: Notification = builder.build()
        // Send notifications of status bar
        manager.notify(notificationId, baseNF)
        notificationId++
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        result(requestCode)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        result(requestCode)
    }

    private fun result(requestCode: Int) {
        when (requestCode) {
            REQUESTCODE_REQUEST_READ_PERMISSION -> {
                Toast.makeText(
                    this@MainActivity,
                    Extentions.getString(R.string.app_close_request_read_permission),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            REQUESTCODE_REQUEST_NOTIFICATION_PERMISSION -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ContextCompat.checkSelfPermission(
                        this@MainActivity, POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    sendNotification()
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        bindingView?.changeLanguageId?.text = Extentions.getString(R.string.app_change_language)
        bindingView?.requestReadPermissionId?.text =
            Extentions.getString(R.string.app_request_read_permission)
        bindingView?.openServiceId?.text = Extentions.getString(R.string.app_open_service)
        bindingView?.sendNotificationId?.text = Extentions.getString(R.string.app_send_notification)


    }
}

