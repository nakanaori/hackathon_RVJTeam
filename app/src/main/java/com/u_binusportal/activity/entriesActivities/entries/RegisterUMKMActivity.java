package com.u_binusportal.activity.entriesActivities.entries;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.pchmn.materialchips.ChipsInput;
import com.pchmn.materialchips.model.ChipInterface;
import com.u_binusportal.Constant;
import com.u_binusportal.component.Category;
import com.u_binusportal.R;
import com.u_binusportal.component.Umkm;
import com.u_binusportal.forTesting.UserTesting;
import com.u_binusportal.handlers.FragmentHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegisterUMKMActivity extends AppCompatActivity {

    private EditText UMKMNameField;
    private EditText AddressField;
    private EditText DescriptionField;
    private ChipsInput category;

    private Button RegisterUMKMButton;
    private ProgressDialog Progress;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_umkm);

        UMKMNameField = findViewById(R.id.addUmkmName);
        AddressField = findViewById(R.id.addUmkmAddress);
        DescriptionField = findViewById(R.id.addUmkmDescription);

        RegisterUMKMButton = findViewById(R.id.registerUmkm);

        Progress = new ProgressDialog(this);

        category = findViewById(R.id.addUmkmCategory);
        ArrayList<com.u_binusportal.component.Category> categoryList = new ArrayList<>();
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


        RegisterUMKMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegisterUMKM();
            }
        });
    }

    private void startRegisterUMKM() {
        String name = UMKMNameField.getText().toString().trim();
        String description = DescriptionField.getText().toString().trim();
        String address = AddressField.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            UMKMNameField.requestFocus();
            UMKMNameField.setError("Nama UMKM tidak boleh kosong");

        } else if (TextUtils.getTrimmedLength(name) > 50) {
            UMKMNameField.requestFocus();
            UMKMNameField.setError("Nama UMKM tidak boleh lebih dari 50 karakter");

        }else if (TextUtils.isEmpty(description)) {
            DescriptionField.requestFocus();
            DescriptionField.setError("Deskripsi tidak boleh kosong");

        } else if(TextUtils.getTrimmedLength(description) > 100){
            DescriptionField.requestFocus();
            DescriptionField.setError("Deskripsi tidak boleh lebih dari 100 karakter");

        } else if (TextUtils.isEmpty(address)) {
            AddressField.requestFocus();
            AddressField.setError("Alamat tidak boleh kosong");

        } else if(TextUtils.getTrimmedLength(address) > 100){
            AddressField.requestFocus();
            AddressField.setError("Alamat tidak boleh lebih dari 100 karakter");
        } else {
            //
            List<Category> selectedList = (List<Category>) category.getSelectedChipList();
            Umkm newUmkm = doAddUMKM(name, description, address, selectedList);

            HashMap<String, Object> hash = storeToHash(newUmkm);
            db.collection("Umkm").document(Constant.currentUser.getUserId()).set(hash);

            Constant.currentUmkm = newUmkm;
            Constant.updateUmkm();
            startActivity(new Intent(this, FragmentHandler.class));
        }

    }

    private Umkm doAddUMKM(String name, String description, String address, List<Category> categories) {

        List<String> listOfCategory = new ArrayList<>();
        for(Category currentCategory : categories) {
            listOfCategory.add(currentCategory.getLabel());
        }
        return new Umkm(name, description, address, listOfCategory);
    }

    private HashMap<String, Object> storeToHash (Umkm currentUmkm) {
        HashMap<String, Object> newHash = new HashMap<>();
        newHash.put("id", currentUmkm.getUmkmId());
        newHash.put("name", currentUmkm.getUmkmName());
        newHash.put("description", currentUmkm.getUmkmDescription());
        newHash.put("address", currentUmkm.getUmkmAddress());
        newHash.put("category", currentUmkm.getUmkmCategory());
        newHash.put("userId", currentUmkm.getUserID());

        // ini udah diwrap di constructor baru sebagai null
        newHash.put("image", currentUmkm.getUmkmImage());
        newHash.put("imageInt", currentUmkm.getImageR());
        return newHash;
    }
}
