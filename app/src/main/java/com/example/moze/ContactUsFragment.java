package com.example.moze;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class ContactUsFragment extends Fragment implements View.OnClickListener {

    Button btnOK;
    EditText txtSubject, txtMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View add_view = inflater.inflate(R.layout.fragment_contactus, container, false);
        txtSubject = (EditText) add_view.findViewById(R.id.txtSubject);
        txtMessage = (EditText) add_view.findViewById(R.id.txtMessage);


        btnOK = (Button) add_view.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);
        return add_view;

    }

    @Override
    public void onClick(View view) {
        String sub = txtSubject.getText().toString();
        String mess = txtMessage.getText().toString();

        Intent mail = new Intent(Intent.ACTION_SEND);
        mail.putExtra(Intent.EXTRA_CC, new String[]{"arrotechdesign@gmail.com"});
        mail.putExtra(Intent.EXTRA_BCC, new String[]{"gitundu@gmail.com"});
        mail.putExtra(Intent.EXTRA_SUBJECT, sub);
        mail.putExtra(Intent.EXTRA_TEXT, mess);
        mail.setType("message/rfc822");
        startActivity(Intent.createChooser(mail, "Send email via:"));
    }
}
