package com.u_binusportal.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pchmn.materialchips.ChipsInput;
import com.u_binusportal.R;
import com.u_binusportal.component.Category;

import java.util.ArrayList;

public class EditProfileUMKM extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_umkm);

        ChipsInput category = (ChipsInput) findViewById(R.id.editUMKMCategory);
        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("C001","Makanan"));
        categoryList.add(new Category("C002","Minuman"));
        categoryList.add(new Category("C003","Pakaian"));
        categoryList.add(new Category("C004","Hobi"));
        categoryList.add(new Category("C005","Kosmetik"));
        categoryList.add(new Category("C006","Pertanian"));
        categoryList.add(new Category("C007","Elektronik"));
        categoryList.add(new Category("C008","Perabotan Rumah Tangga"));
        categoryList.add(new Category("C009","Jasa"));
        categoryList.add(new Category("C010","Survernir"));
        categoryList.add(new Category("C011","Kesehatan"));
        categoryList.add(new Category("C012","Lain-lain"));
        category.setFilterableList(categoryList);

        final EditText editedName = (EditText)findViewById(R.id.editUMKMName);
        EditText editedAddress = (EditText)findViewById(R.id.editUMKMAddress);
        EditText editedDescription = (EditText)findViewById(R.id.editUMKMDescription);
        ChipsInput editedCategegory = (ChipsInput) findViewById(R.id.editUMKMCategory);
        ImageView editedImage = (ImageView) findViewById(R.id.editUmkmImage);
        Button editButton = (Button) findViewById(R.id.editUmkmButton);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("EditedText", editedName.getText().toString());
            }
        });
    }
}
