package gogo.skyborn.com.gogo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Interfaces.GGOnChangeFragmentListener;
import gogo.skyborn.com.gogo.MainActivity;
import gogo.skyborn.com.gogo.Models.GGUser;
import gogo.skyborn.com.gogo.Utils.GGSqlInfo;


/**
 * Created by Sandy on 8/27/2017.
 */

public class GGLogin extends GGBase implements View.OnClickListener {
    private EditText mEditEmail, mEditPassword;
    private LoginButton mBtnFacebook;
    private Button mBtnSign, mBtnLogin;
    private CallbackManager callbackManager;
    private GGOnChangeFragmentListener mOnChange;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gg_fragment_login, container, false);
        mEditEmail = (EditText) view.findViewById(R.id.edit_email);
        mEditPassword = (EditText) view.findViewById(R.id.edit_password);
        mBtnFacebook = (LoginButton) view.findViewById(R.id.btn_facebook);
        mBtnSign = (Button) view.findViewById(R.id.btn_sign);
        mBtnLogin = (Button) view.findViewById(R.id.btn_login);
        mBtnFacebook.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnSign.setOnClickListener(this);
        return view;
    }

    public void setmOnChange(GGOnChangeFragmentListener mOnChange) {
        this.mOnChange = mOnChange;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        if (view == mBtnFacebook) {
            FacebookSdk.sdkInitialize(getContext());
            callbackManager = CallbackManager.Factory.create();
            if (mBtnFacebook != null) {
                mBtnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        GraphRequest data_request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject json_object, GraphResponse response) {
                                        GGUser user = new GGUser(json_object);
                                        Intent intent = new Intent(getContext(),MainActivity.class);
                                        intent.putExtra("user", user);
                                        startActivity(intent);
                                    }

                                });
                        Bundle permission_param = new Bundle();
                        permission_param.putString("fields", "id,name,email");
                        data_request.setParameters(permission_param);
                        data_request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Log.e("Cancel Login Facebook", "User Cancel Facebook Login");
                    }

                    @Override
                    public void onError(FacebookException e) {
                        Log.e("Error Login Facebook", e.getMessage());
                    }
                });
            }
        } else if (view == mBtnLogin) {
            if (mEditEmail.getText().toString() == null || mEditEmail.getText().toString().isEmpty() || mEditPassword.getText().toString() == null || mEditPassword.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Falta correo/contraseña", Toast.LENGTH_LONG).show();
            } else {
                GGSqlInfo ggSqlInfo = new GGSqlInfo(getContext());
                boolean isPassword = ggSqlInfo.checkPassword(mEditEmail.getText().toString(), mEditPassword.getText().toString());
                GGUser user = ggSqlInfo.findUser(mEditEmail.getText().toString());
                if (isPassword) {
                    Intent i = new Intent(getContext(), MainActivity.class);
                    i.putExtra("user", user);
                    startActivity(i);
                    getActivity().finish();
                } else {
                    Toast.makeText(getContext(), "Correo/contraseña incorrecto", Toast.LENGTH_LONG).show();
                }
            }
        } else if (view == mBtnSign) {
            if (mOnChange != null) {
                mOnChange.changeFragment(new GGPagerFirstTime(), "pagerRegister");
            }
        }
    }
}