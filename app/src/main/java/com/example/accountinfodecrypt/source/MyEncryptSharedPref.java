package com.example.accountinfodecrypt.source;

import android.content.Context;

import androidx.security.crypto.EncryptedFile;
import androidx.security.crypto.MasterKey;

import com.example.accountinfodecrypt.model.AccountInfo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;

/**
 * Created by Nguyen Tuan Anh on 19/07/2022.
 * FPT Software
 * tuananhprogrammer@gmail.com
 */
public class MyEncryptSharedPref {
    private static MyEncryptSharedPref sharedPref;
    private MyEncryptSharedPref() {
    }

    public static MyEncryptSharedPref getInstance() {
        if (sharedPref == null) {
            sharedPref = new MyEncryptSharedPref();
        }
        return sharedPref;
    }

    public void writeFile(Context context, String path, String fileToWrite, AccountInfo accountInfo) {
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();
            EncryptedFile encryptedFile = new EncryptedFile.Builder(
                    context,
                    new File(path, fileToWrite),
                    masterKey,
                    EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build();

            OutputStream outputStream = encryptedFile.openFileOutput();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(accountInfo);
            oos.flush();
            oos.close();
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public AccountInfo readFile(Context context, String path, String fileToRead) {
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();
            EncryptedFile encryptedFile = new EncryptedFile.Builder(
                    context,
                    new File(path, fileToRead),
                    masterKey,
                    EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build();

            InputStream inputStream = encryptedFile.openFileInput();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            AccountInfo accountInfo = (AccountInfo) ois.readObject();
            ois.close();
            return accountInfo;
        } catch (GeneralSecurityException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
