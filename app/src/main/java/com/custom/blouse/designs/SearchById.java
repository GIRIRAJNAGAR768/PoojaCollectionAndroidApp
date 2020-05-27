package com.custom.blouse.designs;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchById extends AppCompatActivity {

    Button showDesign;
    EditText idEdit;
    String id = null,link;
    ImageView design;
    DatabaseReference db;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_id);

        showDesign = findViewById(R.id.showDesign);
        idEdit = findViewById(R.id.id);
        design = findViewById(R.id.design);

        pd = new ProgressDialog(SearchById.this);
        pd.setMessage("Loading please wait...");


        db = FirebaseDatabase.getInstance().getReference().child("Designs");

        showDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(idEdit.getWindowToken(), 0);


                design.setVisibility(View.INVISIBLE);


                id = idEdit.getText().toString();
                if(!id.isEmpty())
                {

                    searchImages(id);
                }
                else
                {
                    design.setVisibility(View.INVISIBLE);
                    Toast.makeText(SearchById.this, "Please enter design id", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void searchImages(String id)
    {
        pd.show();
        db.child(id+"").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null)
                {
                    pd.dismiss();
                    design .setVisibility(View.VISIBLE);
                    link = dataSnapshot.child("Link").getValue()+"";
                    Glide.with(SearchById.this).load(link).into(design);
                }
                else
                {
                    pd.dismiss();
                    Toast.makeText(SearchById.this, "design not found.", Toast.LENGTH_SHORT).show();
                }

//                Toast.makeText(SearchById.this, dataSnapshot.getValue()+",", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(),"Unknown Error...",Toast.LENGTH_LONG).show();
            }
        });
    }
}
