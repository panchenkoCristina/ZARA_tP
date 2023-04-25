package com.example.zara_tpv.windows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zara_tpv.R;
import com.example.zara_tpv.adapter.ListClothesAdapter;
import com.example.zara_tpv.manager.DialogManager;
import com.example.zara_tpv.manager.ListManager;
import com.example.zara_tpv.pojo.ListClothes;

import java.util.ArrayList;
import java.util.List;


public class ResumeShopWindow extends AppCompatActivity implements View.OnClickListener {
    private List<ListClothes> clothes = new ArrayList<>();
    private ListClothesAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_shop_window);

        clothes = ListManager.getClothes();
        setAdapter();
        setRecyclerView((RecyclerView) findViewById(R.id.recyclerView_clothes));
        setToolbar((Toolbar) findViewById(R.id.toolbar_menu));
        setButtons((Button) findViewById(R.id.button_openScannerCode),
                (Button) findViewById(R.id.button_openDialogCode));
    }

    private void setAdapter() {
        adapter =  new ListClothesAdapter(clothes, new ListClothesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListClothes clothe) {
                DialogManager.openDialogClothe(ResumeShopWindow.this, adapter, clothe, clothes.indexOf(clothe));
            }
        });
    }

    private void setButtons(Button openDialog, Button openScanner) {
        openDialog.setOnClickListener(this);
        openScanner.setOnClickListener(this);
    }

    private void setToolbar(Toolbar toolbar) {
        toolbar.setTitle(R.string.title_shop);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon_menu);
        toolbar.setOverflowIcon(drawable);
        setSupportActionBar(toolbar);
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general_options_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_dresser:
                startActivity(new Intent(this, ShopWindow.class));
                break;
            case R.id.action_account:
                DialogManager.openDialogLogin(this);
                break;
            case R.id.action_shopping:
                Toast.makeText(this, "Shopping", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        switch (b.getId()) {
            case R.id.button_openDialogCode:
                DialogManager.openDialogCode(this);
                break;
            case R.id.button_openScannerCode:
                startActivity(new Intent(this, ScannerWindow.class));
                break;
        }
    }


}
