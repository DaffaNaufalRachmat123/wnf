package com.android.wnf;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import kotlin.Suppress;

public class ActivityAbout extends AppCompatActivity {
    private ImageView icHome;
    private AppCompatTextView aboutText;
    private String about_text = "<p><strong>WNF </strong><strong>adalah</strong> <strong>Aplikasi</strong> <strong>berbasis</strong><strong> android yang </strong><strong>dikembangkan</strong> <strong>untuk</strong> <strong>membantu</strong> <strong>pembelajaran</strong> <strong>matematika</strong> <strong>baik</strong> <strong>disekolah</strong> <strong>maupun</strong> <strong>diluar</strong> <strong>sekolah</strong><strong>.</strong></p>\n" +
            "<p><strong>Software:</strong></p>\n" +
            "<p>1.&nbsp;&nbsp; <strong>Power Point 2010</strong></p>\n" +
            "<p>2.&nbsp;&nbsp; <strong>iSpring</strong><strong> Suite 10</strong></p>\n" +
            "<p>3.&nbsp;&nbsp; <strong>Android Studio</strong><strong><br /></strong></p>\n" +
            "<p><strong>Backsoud</strong><strong> :</strong></p>\n" +
            "<p>1.&nbsp;&nbsp; <strong>Lagu</strong><strong> 1</strong></p>\n" +
            "<p>2.&nbsp;&nbsp; <strong>Lagu</strong><strong> 2</strong></p>\n" +
            "<ul>\n" +
            "<li>&nbsp;</li>\n" +
            "<li><strong>\t\tTim </strong><strong>Pengembangan</strong><strong> : Muhammad </strong><strong>Hafidh</strong> <strong>Ma&rsquo;ruf</strong><strong>, </strong><strong>dan</strong> <strong>Bapak</strong> <strong>Wahidin</strong> <strong>Mp.d</strong><strong>.</strong></li>\n" +
            "<li><strong>\t\tValidator Media : </strong><strong>Bapak</strong><strong> Benny </strong><strong>Hendriana</strong> <strong>Mp.d</strong></li>\n" +
            "<li><strong>\t\tValidator </strong><strong>Materi</strong><strong> : </strong><strong>Ibu</strong> <strong>Nenih</strong> <strong>M.pd</strong></li>\n" +
            "</ul>";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        icHome = findViewById(R.id.icHome);
        aboutText = findViewById(R.id.aboutText);
        aboutText.setText(fromHtml(about_text));

        icHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private Spanned fromHtml(String data){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(data, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(data);
        }
    }
}
