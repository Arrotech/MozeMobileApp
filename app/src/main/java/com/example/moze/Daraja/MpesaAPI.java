package com.example.moze.Daraja;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moze.Daraja.model.AccessToken;
import com.example.moze.Daraja.model.LNMExpress;
import com.example.moze.Daraja.model.LNMResult;
import com.example.moze.Daraja.util.TransactionType;
import com.example.moze.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MpesaAPI extends AppCompatActivity{

    @BindView(R.id.editTextPhoneNumber)
    EditText editTextPhoneNumber;
    @BindView(R.id.sendButton)
    Button sendButton;

    //Declare Daraja :: Global Variable
    Daraja daraja;

    String phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mpesa_api);
        ButterKnife.bind(this);
        //Init Daraja
        //TODO :: REPLACE WITH YOUR OWN CREDENTIALS  :: THIS IS SANDBOX DEMO
        daraja = Daraja.with("z6alt0QLjzLbyTHiG3rGVgrLyDslJsPp", "UMrtrGLg0qrEMNfh", new DarajaListener<AccessToken>() {
            @Override
            public void onResult(@NonNull AccessToken accessToken) {
                Log.i(MpesaAPI.this.getClass().getSimpleName(), accessToken.getAccess_token());
                Toast.makeText(MpesaAPI.this, "TOKEN : " + accessToken.getAccess_token(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Log.e(MpesaAPI.this.getClass().getSimpleName(), error);
            }
        });

        //TODO :: THIS IS A SIMPLE WAY TO DO ALL THINGS AT ONCE!!! DON'T DO THIS :)
        sendButton.setOnClickListener(v -> {

            //Get Phone Number from User Input
            phoneNumber = editTextPhoneNumber.getText().toString().trim();

            if (TextUtils.isEmpty(phoneNumber)) {
                editTextPhoneNumber.setError("Please Provide a Phone Number");
                return;
            }

            //TODO :: REPLACE WITH YOUR OWN CREDENTIALS  :: THIS IS SANDBOX DEMO
            LNMExpress lnmExpress = new LNMExpress(
                    "174379",
                    "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",  //https://developer.safaricom.co.ke/test_credentials
                    TransactionType.CustomerBuyGoodsOnline, // TransactionType.CustomerPayBillOnline  <- Apply any of these two
                    "10",
                    "254708374149",
                    "174379",
                    phoneNumber,
                    "http://mpesa-requestbin.herokuapp.com/120bspz1",
                    "001ABC",
                    "Goods Payment"
            );

            //This is the
            daraja.requestMPESAExpress(lnmExpress,
                    new DarajaListener<LNMResult>() {
                        @Override
                        public void onResult(@NonNull LNMResult lnmResult) {
                            Log.i(MpesaAPI.this.getClass().getSimpleName(), lnmResult.ResponseDescription);
                            Toast.makeText(MpesaAPI.this, "Ok", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(String error) {
                            Log.i(MpesaAPI.this.getClass().getSimpleName(), error);
                            Toast.makeText(MpesaAPI.this, "Error:(", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        });
    }
}
