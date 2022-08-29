package com.example.musculaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.musculaction.adapter.CategoryAdapter;
import com.example.musculaction.data.DatabaseHelper;
import com.example.musculaction.model.Category;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        String[] categNames;
        int[] categImg;
        int[] categId;

        //FIXME
        //cand dau click pe categorie pun in intent put extra id si fechuiesc categoria according to id primit
        //list view 2nd activity in recicle view ca se se updateze imediat

        //DatabaseHelper
        DatabaseHelper db = new DatabaseHelper(MainActivity.this);

//        Category cuisses = new Category();
//        cuisses.setTitle("Cuisses");
//        cuisses.setImage("R.drawable.cuisses");
//
//        db.addCategory(cuisses);

        List<Category> categoryList = db.getAllCategories();

        categNames = new String[categoryList.size()];
        categImg = new int[categoryList.size()];
        categId = new int[categoryList.size()];

        //extract category name and image from db
        //FIXME i an move it to fatabase helper in 2 methods
            int i = 0;
            for (Category category : categoryList) {
                Log.d("MainAct", "onCreate: " + category.getImage());

                categNames[i] = category.getTitle();
                categImg[i] = category.getImage();
                categId[i] = category.getId();
                i++;
            }
            Log.d("MainAc", "CAtegID: " + Arrays.toString(categImg));

        CategoryAdapter categoryAdapter = new CategoryAdapter(MainActivity.this,categNames,categImg);
        gridView.setAdapter(categoryAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(MainActivity.this,"You cliked: "+categId[i],Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ExercicesActivity.class);
                intent.putExtra("id",categId[i]);
                startActivity(intent);
            }
        });
    }
}