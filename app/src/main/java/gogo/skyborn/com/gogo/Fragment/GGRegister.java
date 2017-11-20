package gogo.skyborn.com.gogo.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Fragment.GGBase;
import gogo.skyborn.com.gogo.Interfaces.GGOnChangeFragmentListener;
import gogo.skyborn.com.gogo.Models.GGUser;
import gogo.skyborn.com.gogo.Utils.GGSqlInfo;

/**
 * Created by Sandy on 11/12/2017.
 */

public class GGRegister extends GGBase implements View.OnClickListener {
    private ImageView mCamera, mUser;
    private Button mRegister;
    private EditText mEditEmail, mEditPassword, mEditPasswordRepeat, mEditUserName;
    private GGOnChangeFragmentListener mOnChangePage;
    public static String mUserName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gg_fragment_register, container, false);
        mEditEmail = (EditText) view.findViewById(R.id.edit_email);
        mCamera = (ImageView) view.findViewById(R.id.img_camera);
        mUser = (ImageView) view.findViewById(R.id.img_user);
        mEditPassword = (EditText) view.findViewById(R.id.edit_password);
        mEditPasswordRepeat = (EditText) view.findViewById(R.id.edit_password_repeat);
        mEditUserName = (EditText) view.findViewById(R.id.edit_user);
        mRegister = (Button) view.findViewById(R.id.btn_register);
        mRegister.setOnClickListener(this);
        mCamera.setOnClickListener(this);
        return view;
    }

    public void setmOnChangePage(GGOnChangeFragmentListener mOnChangePage){
        this.mOnChangePage = mOnChangePage;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if (mUser != null) {
                mUser.setImageBitmap(imageBitmap);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mRegister) {
            if ((mEditUserName != null && mEditUserName.getText().toString().equals("")) || (mEditEmail != null && mEditEmail.getText().toString().equals("")) || (mEditPassword != null && mEditPassword.getText().toString().equals("")) || (mEditPasswordRepeat != null && mEditPasswordRepeat.getText().toString().equals(""))) {
                Toast.makeText(getContext(), "Por favor, llena todos los datos", Toast.LENGTH_SHORT).show();
            } else {
                if (mEditPasswordRepeat.getText().toString().equals(mEditPassword.getText().toString())) {
                    GGSqlInfo db = new GGSqlInfo(getContext());
                    if (db != null) {
                        GGUser user = new GGUser();
                        if (mEditUserName != null) {
                            user.setmName(mEditUserName.getText().toString());
                            user.setmEmail(mEditEmail.getText().toString());
                            user.setmPassword(mEditPassword.getText().toString());
                            mUserName = mEditUserName.getText().toString();
                        }
                        db.addUser(user);
                        if(mOnChangePage != null) {
                            mOnChangePage.changeFragment(null,"timeWake");
                        }
                    }
                }else {
                    Toast.makeText(getContext(), "Contraseña no coincide", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (view == mCamera) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(getContext().getPackageManager()) != null) {
                startActivityForResult(cameraIntent, 1);
            }
        }
    }
}