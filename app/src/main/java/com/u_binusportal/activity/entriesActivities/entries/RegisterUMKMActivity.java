package com.u_binusportal.activity.entriesActivities.entries;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.pchmn.materialchips.ChipsInput;
import com.pchmn.materialchips.model.ChipInterface;
import com.u_binusportal.Constant;
import com.u_binusportal.MainActivity;
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
    private TextView trigger;
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

//        category = findViewById(R.id.addUmkmCategory);
//        ArrayList<com.u_binusportal.component.Category> categoryList = new ArrayList<>();
//        categoryList.add(new Category("C001","Makanan"));
//        categoryList.add(new Category("C002","Minuman"));
//        categoryList.add(new Category("C003","Pakaian"));
//        categoryList.add(new Category("C004","Hobi"));
//        categoryList.add(new Category("C005","Kosmetik"));
//        categoryList.add(new Category("C006","Pertanian"));
//        categoryList.add(new Category("C007","Elektronik"));
//        categoryList.add(new Category("C008","Perabotan Rumah Tangga"));
//        categoryList.add(new Category("C009","Jasa"));
//        categoryList.add(new Category("C010","Survernir"));
//        categoryList.add(new Category("C011","Kesehatan"));
//        categoryList.add(new Category("C012","Lain-lain"));
//        category.setFilterableList(categoryList);

        trigger = findViewById(R.id.tv_categories_regUMKM);

        final boolean [] checkedItems = new boolean[13];
        final String [] categories = getResources().getStringArray(R.array.category);
        final ArrayList<Integer> selectedCategory = new ArrayList<>();
        final List<String> listOfSelectedCategory = new ArrayList<>();
        final List<String> tempSelected = new ArrayList<>();

        trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog_cat = new AlertDialog.Builder(RegisterUMKMActivity.this);
                dialog_cat.setTitle("Choose any categories");
                dialog_cat.setMultiChoiceItems(categories, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked) {
                            if(!selectedCategory.contains(position)) {
                                selectedCategory.add(position);
                            } else {
                                selectedCategory.remove(position);
                            }
                        }
                    }
                });

                dialog_cat.setCancelable(false);
                dialog_cat.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String res = "";
                        tempSelected.clear();
                        trigger.setText("");
                        for (int idx : selectedCategory) {
                            res = res + categories[idx];
                            if(idx == selectedCategory.get(selectedCategory.size()-1)) break;
                            res = res + ", ";

                            // ini refresh category terpilih
                            if(tempSelected.isEmpty()) tempSelected.add(categories[idx]);
                            if(!tempSelected.contains(categories[idx]))
                                tempSelected.add(categories[idx]);
                        }
                        trigger.setText(res);
                    }
                });

                dialog_cat.setNegativeButton("Return", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = dialog_cat.create();
                mDialog.show();
            }
        });

        RegisterUMKMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOfSelectedCategory.addAll(tempSelected);
                startRegisterUMKM(listOfSelectedCategory);
            }
        });

    }

    private void startRegisterUMKM(List<String> cat) {
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

            // List<Category> selectedList = (List<Category>) category.getSelectedChipList();

            Umkm newUmkm = new Umkm(name, description, address, cat);

            HashMap<String, Object> hash = storeToHash(newUmkm);
            db.collection("Umkm").document(Constant.currentUser.getUserId()).set(hash);

            Constant.currentUmkm = newUmkm;
            Constant.updateUmkm();
            startActivity(new Intent(this, FragmentHandler.class));
        }

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
