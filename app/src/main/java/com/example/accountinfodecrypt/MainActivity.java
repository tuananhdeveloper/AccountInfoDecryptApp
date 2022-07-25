package com.example.accountinfodecrypt;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.accountinfodecrypt.adapter.MyAdapter;
import com.example.accountinfodecrypt.dialog.MyPasswordDialog;
import com.example.accountinfodecrypt.model.AccountInfo;
import com.example.accountinfodecrypt.model.AccountItem;
import com.example.accountinfodecrypt.model.Password;
import com.example.accountinfodecrypt.source.AccountInfoData;
import com.example.accountinfodecrypt.source.MyEncryptSharedPref;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {
    public static final String EXTRA_ACCOUNT_INFO = "Extra account info";
    public static final String parentPath = "storage/0/emulated";
    private static final int REQUEST_CODE = 223;

    private RecyclerView recyclerView;
    private TextView textNothing;
    private MyAdapter adapter;
    private List<AccountInfo> accountInfoList = new ArrayList<>();
    private List<AccountItem> accountItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                checkPermission();
            } catch (DecoderException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onItemClick(int position, View view, boolean isLongClick) {
        if (!isLongClick) {
            AccountInfo accountInfo = accountInfoList.get(position);
            AccountItem accountItem = accountItemList.get(position);

            MyPasswordDialog passwordDialog = new MyPasswordDialog((password, isPositiveButtonClick, dialog, id) -> {
                if (password.getPassword().equals(accountItem.getPassword())) {
                    String fileName = "account_info_" + accountInfo.getUsername() + "_"  + accountInfo.getWebsite();
                    AccountInfo newAccountInfo = MyEncryptSharedPref.getInstance()
                            .readFile(this, parentPath, fileName);
                    navigateToDetailActivity(newAccountInfo);
                }
            });
            passwordDialog.show(getSupportFragmentManager(), null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        initData();
                    } catch (DecoderException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    //Explain to the user that the feature is unavailable
                }
                return;
        }
    }

    private void initView() {
        textNothing = findViewById(R.id.text_nothing);
        recyclerView = findViewById(R.id.my_recyclerview);
        adapter = new MyAdapter(this, accountInfoList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission() throws DecoderException {
        // Check if the permission has been granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is already available
            initData();
        } else {
            // Permission is missing and must be requested.
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    private void initData() throws DecoderException {
        String[] projection = {
                AccountInfoData.Account._ID,
                AccountInfoData.Account._TITLE,
                AccountInfoData.Account._CONTENT,
                AccountInfoData.Account._PASSWORD
        };

        Cursor cursor = getContentResolver().query(AccountInfoData.CONTENT_URI, projection,
                null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(AccountInfoData.Account._ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(AccountInfoData.Account._TITLE));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(AccountInfoData.Account._CONTENT));
                String encryptedPassword = cursor.getString(cursor.getColumnIndexOrThrow(AccountInfoData.Account._PASSWORD));
                String password = new String(Hex.decodeHex(encryptedPassword.toCharArray()));
                AccountItem accountItem = new AccountItem(id, title, content, password);
                accountItemList.add(accountItem);
            }
            cursor.close();
        }

        showAll();
    }

    private void showAll() {
        for(AccountItem accountItem: accountItemList) {
            textNothing.setVisibility(View.GONE);
            String username = accountItem.getTitle().split(":")[0];
            String website = accountItem.getTitle().split(":")[1];

            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setUsername(username);
            accountInfo.setWebsite(website);
            accountInfoList.add(accountInfo);
            adapter.notifyItemInserted(accountItemList.size() - 1);
        }
    }

    private void navigateToDetailActivity(AccountInfo accountInfo) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_ACCOUNT_INFO, accountInfo);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}