package com.custom.blouse.designs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class Gallery_Show extends AppCompatActivity {
    FirebaseDatabase fb;
    DatabaseReference db;
    List<String> links = new ArrayList<>();
    List<String> code = new ArrayList<>();
    String Code;
    ProgressDialog pd;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_show);

        fb = FirebaseDatabase.getInstance();
        db = fb.getReference();

        pd = new ProgressDialog(Gallery_Show.this);
        pd.setMessage("Loading...");
        pd.show();

        recyclerView = findViewById(R.id.recycle);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        db=db.child("Designs");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                links.clear();
                for(DataSnapshot shot : dataSnapshot.getChildren()){
                        links.add(shot.child("Link").getValue().toString());
                        code.add(shot.getKey());
                        show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void show(){
        if(!links.isEmpty()){
            pd.dismiss();
            recyclerView.setAdapter(new GalleryGridAdapter(Gallery_Show.this, links, Code, code));
        }
        else {
            pd.setTitle("Error Loading... ");
            pd.setMessage("Try Again Later");
        }
    }
}
