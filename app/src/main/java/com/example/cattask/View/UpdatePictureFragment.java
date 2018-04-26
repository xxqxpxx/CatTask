package com.example.cattask.View;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cattask.Controller.FileUtils;
import com.example.cattask.Controller.ServiceGenerator;
import com.example.cattask.Model.Image;
import com.example.cattask.R;
import com.example.cattask.WebService.Api;

import java.io.ByteArrayOutputStream;
import java.io.File;

import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cattask.View.MainActivity.userdata;


public class UpdatePictureFragment extends Fragment {

    Button selectUploadButton;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "Path_to_your_server";
    private Uri uri;

    public UpdatePictureFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_picture, container, false);

         selectUploadButton =  view.findViewById(R.id.select_image);
        selectUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EasyPermissions.hasPermissions(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                    openGalleryIntent.setType("image/*");
                    startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
                } else {
                    // Do not have permissions, request them now
                    EasyPermissions.requestPermissions(getActivity(), "Gallery Permission",
                            READ_REQUEST_CODE,  Manifest.permission.READ_EXTERNAL_STORAGE);
                }


            }
        });

        return  view;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void uploadFile(Uri fileUri)  {
        // create upload service client
        Api service =
                ServiceGenerator.createService(Api.class);

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(getContext(), fileUri);

     //   File imagefile = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() ,file.getAbsolutePath() );

        Bitmap selectedImage =  BitmapFactory.decodeFile(file.getAbsolutePath() );
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 30, stream);
        byte[] byteArray = stream.toByteArray();
        String strBase64=Base64.encodeToString(byteArray, 0);

        Image image = new Image();
        image.setImage_profile(strBase64);

        Call<Object> call = service.uploadPicture(userdata.getId().toString() ,  image);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call,
                                   Response<Object> response) {
                Log.v("Upload", "success");
                Toast.makeText(getActivity(), response.body().toString() , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
                Toast.makeText(getActivity(), "Check internet", Toast.LENGTH_LONG).show();

            }
        });
    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {

        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.NO_WRAP);
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            uri = data.getData();
                uploadFile(uri);
        }
    }
}
