package com.phong.hocnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        String s = i.getStringExtra("CHI TIẾT!");
        TextView txtChiTiet = (TextView) findViewById(R.id.txtChiTiet);
        txtChiTiet.setText(s);
    }

    public void XuLyTaoNotification(View view) {
        //Tạo đối tượng builder cho NotificationCompat
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
        //Thiết lập icon cho Notification
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        //Thiết lập tiêu đề
        builder.setContentTitle("Có thông báo!");
        //Thiết lập nội dung cho Notification
        builder.setContentText("Nhấn vào đây để xem chi tiết!");
        //Thiết lập âm thanh cho Notification
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));//Lấy ăm thanh có sẵn trong điện thoại

        Intent resultIntent = new Intent(this, MainActivity.class);//Mở ở màn hình Main
        //Thông tin chi tiết
        String s = "Hoàng Tuấn Phong và Phan Anh là Bạn Thân!";
        resultIntent.putExtra("CHI TIẾT!",s);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(resultIntent);

        //Thiết lập thông số cho Intent để khi mở thông báo nhỏ thì ko kićh hoạt màn hình Notification
        PendingIntent resultPendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(resultPendingIntent);

        //Lấy đội tượng NotificationManager ra
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//trả về 1 đối tượng
        //Show thông báo:
        //Đối số 1 là mã đại diện cho thông báo, đối số 2 là hiển thi thông báo
        notificationManager.notify(123, builder.build());
    }

    public void XuLyDongNotification(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(123);
    }
}
