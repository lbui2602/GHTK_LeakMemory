package com.example.memoryleak

import android.app.Service
import android.content.Intent
import android.os.IBinder

class LeakyService : Service() {

    companion object {
        // Giữ tham chiếu tĩnh đến Service, gây ra Memory leak
        var instance: LeakyService? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this // Tạo Memory leak bằng cách giữ tham chiếu đến Service
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Thực hiện một số tác vụ ngầm
        return START_STICKY // Giữ service hoạt động ngay cả khi ứng dụng bị tắt
    }

    override fun onDestroy() {
        super.onDestroy()
        // Không giải phóng tham chiếu, gây ra Memory leak
        // instance = null
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
