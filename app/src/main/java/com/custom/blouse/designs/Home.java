package com.custom.blouse.designs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class Home extends AppCompatActivity {

    LinearLayout gallery,searchById;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        gallery = findViewById(R.id.galery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Gallery_Show.class);
                startActivity(intent);
            }
        });

        searchById = findViewById(R.id.searchById);
        searchById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,SearchById.class);
                startActivity(intent);
            }
        });

    }
}
