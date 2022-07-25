package com.example.accountinfodecrypt.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.accountinfodecrypt.R;
import com.example.accountinfodecrypt.model.Password;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Created by Nguyen Tuan Anh on 18/07/2022.
 * FPT Software
 * tuananhprogrammer@gmail.com
 */
public class MyPasswordDialog extends DialogFragment {
    private final OnButtonClickListener onButtonClickListener;

    public MyPasswordDialog(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_password, null);
        TextInputLayout inputPassword = view.findViewById(R.id.input_password);
        TextInputLayout inputConfirmPassword = view.findViewById(R.id.input_confirm_password);

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                    if(inputPassword.getEditText() != null && inputConfirmPassword.getEditText() != null) {
                        String password = inputPassword.getEditText().getText().toString();
                        String confirmPassword = inputConfirmPassword.getEditText().getText().toString();
                        if(!TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword)
                            && password.equals(confirmPassword)) {
                            Password mPassword = new Password(password, confirmPassword);
                            onButtonClickListener.onClick(mPassword, true,dialog, id);
                        }
                    }

                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                    onButtonClickListener.onClick(null, false,dialog, id);
                });
        return builder.create();
    }

    public interface OnButtonClickListener {
        void onClick(Password password, boolean isPositiveButtonClick, DialogInterface dialog, int id);
    }
}
