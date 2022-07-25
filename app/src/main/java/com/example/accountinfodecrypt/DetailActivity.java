package com.example.accountinfodecrypt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.accountinfodecrypt.model.AccountInfo;

public class DetailActivity extends AppCompatActivity {
    private TextView textWebsite;
    private TextView textUsername;
    private TextView textNameOnCard;
    private TextView textCardNumber;
    private TextView textExpiryDate;
    private TextView textCVV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initData();
    }

    private void initView() {
        textWebsite = findViewById(R.id.text_website);
        textUsername = findViewById(R.id.text_username);
        textNameOnCard = findViewById(R.id.text_name);
        textCardNumber = findViewById(R.id.text_cardnumber);
        textExpiryDate = findViewById(R.id.text_expiry_date);
        textCVV = findViewById(R.id.text_cvv);
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            AccountInfo accountInfo = (AccountInfo) intent.getExtras()
                    .getSerializable(MainActivity.EXTRA_ACCOUNT_INFO);

            textWebsite.setText(accountInfo.getWebsite());
            textUsername.setText(accountInfo.getUsername());
            textNameOnCard.setText(accountInfo.getNameOnCard());
            textCardNumber.setText(String.valueOf(accountInfo.getCardNumber()));
            textExpiryDate.setText(accountInfo.getExpiryDate());
            textCVV.setText(accountInfo.getCVV());
        }
    }
}