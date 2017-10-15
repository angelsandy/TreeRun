package fittree.skyborn.com.gogo.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import fittree.skyborn.com.gogo.R;


/**
 * Created by Sandy on 8/27/2017.
 */

public class GGLogin extends Fragment implements View.OnClickListener {
    EditText mEditEmail,mEditPassword;
    LoginButton mBtnFacebook;
    Button mBtnSign,mBtnLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gg_fragment_login,container);
        mEditEmail = (EditText)view.findViewById(R.id.edit_email);
        mEditPassword = (EditText)view.findViewById(R.id.edit_password);
        mBtnFacebook = (LoginButton)view.findViewById(R.id.btn_facebook);
        mBtnSign = (Button)view.findViewById(R.id.btn_sign);
        mBtnLogin = (Button)view.findViewById(R.id.btn_login);
        mBtnFacebook.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnSign.setOnClickListener(this);
        return null;
    }

    @Override
    public void onClick(View view) {
        if(view == mBtnFacebook) {
            FacebookSdk.sdkInitialize(getContext());
            CallbackManager callbackManager = CallbackManager.Factory.create();
            if(mBtnFacebook != null) {
                mBtnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                    }

                    @Override
                    public void onCancel() {
                        Log.e("Cancel Login Facebook" , "User Cancel Facebook Login");
                    }

                    @Override
                    public void onError(FacebookException e) {
                        Log.e("Error Login Facebook" , e.getMessage());
                    }
                });
            }
        }else if(view == mBtnLogin) {

        }else if(view == mBtnSign) {

        }
    }
}
