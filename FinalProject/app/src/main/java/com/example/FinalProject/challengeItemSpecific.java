package com.example.FinalProject;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.util.ArrayList;



public class challengeItemSpecific extends Fragment {
    private int num;
    private int number = 0;
    private ImageView[] newActivity;
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_iMAGE = 2;
    private static final String TAG = "";
    private File tempFile;
    private Boolean isPermission = true;

    public void setItem(challengeItem newOne) {
        item.clone(newOne);
    }

    challengeItem item = new challengeItem();
    TextView textPointTotal;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_item_specific, container, false);

        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textDescription = view.findViewById(R.id.textDescription);
        textTitle.setText(item.title);
        textDescription.setText(item.des);
        textPointTotal = view.findViewById(R.id.textPointTotal);
        textPointTotal.setText(String.valueOf(item.chalPoint));
        TextView enrolldate = view.findViewById(R.id.enrolldate);
        enrolldate.setText(item.regdate + " ~ " + item.deadline);

        tedPermission();

        LinearLayout layoutImages = view.findViewById(R.id.layoutImages);
        LinearLayout rowImages = null;
        LinearLayout.LayoutParams settingRow = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        settingRow.setMargins(0, 40, 0, 0);

        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPermission) {
                    goToAlbum();
                }
                else Toast.makeText(view.getContext(), getResources().getString(R.string.permission_2), Toast.LENGTH_LONG).show();
            }
        };

        newActivity = new ImageView[item.chalPoint / 100];
        // 이미지 동적 생성
        for (int i = 0; i < item.chalPoint / 100; i++) {
            num = i;
            if (i % 3 == 0) {
                rowImages = new LinearLayout(this.getContext());
                rowImages.setOrientation(LinearLayout.HORIZONTAL);
                rowImages.setLayoutParams(settingRow);
            }
            newActivity[num] = new ImageView(this.getContext());
            newActivity[num].setTag(num);
            LinearLayout slotActivity = new LinearLayout(this.getContext());
            slotActivity.setOrientation(LinearLayout.HORIZONTAL);

            newActivity[num].setImageResource(R.drawable.image_default);

            newActivity[num].setOnClickListener(myListener);
            //number = num;

            LinearLayout.LayoutParams settingImage = new LinearLayout.LayoutParams(400, 400);
            settingImage.setMargins(10, 0, 10, 0);
            newActivity[num].setLayoutParams(settingImage);
            newActivity[num].setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams settingSlot = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            settingSlot.weight = 1;
            settingSlot.gravity = Gravity.CENTER;
            slotActivity.setLayoutParams(settingSlot);
            slotActivity.addView(newActivity[num]);
            rowImages.addView(slotActivity);
            if (i % 3 == 2 || i + 1 == item.chalPoint / 100) {
                layoutImages.addView(rowImages);
            }

        }

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this.getContext(), "취소 되었습니다.", Toast.LENGTH_SHORT).show();

            if (tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e(TAG, tempFile.getAbsolutePath() + " 삭제 성공");
                        tempFile = null;
                    }
                }
            }
            return;
        }

        if (requestCode == PICK_FROM_ALBUM) {
            Uri photoUri = data.getData();
            Log.d(TAG, "PICK_FROM_ALBUM photoUri : " + photoUri);

            Cursor cursor = null;

            try {

                String[] proj = {MediaStore.Images.Media.DATA};

                assert photoUri != null;
                cursor = getActivity().getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

                Log.d(TAG, "tempFile Uri : " + Uri.fromFile(tempFile));

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();

        } else if (requestCode == PICK_FROM_CAMERA) {

            setImage();

        }
    }

    private void goToAlbum() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    private void setImage() {

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        Log.d(TAG, "setImage : " + tempFile.getAbsolutePath());

        newActivity[number].setImageBitmap(originalBm);
        //num_setImage();
        tempFile = null;
        number+=1;
        challengeItem newOne = new challengeItem();
        newOne.progress+=1;
        if(number ==  item.chalPoint / 100)
        {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            challengeComplete newPage = new challengeComplete();
            Bundle extras = new Bundle();
            extras.putString("title", item.title);
            extras.putString("des", item.des);
            extras.putInt("chalPoint", item.chalPoint);
            extras.putString("regdate", item.regdate);
            extras.putString("deadline", item.deadline);
            newPage.setArguments(extras);

            fragmentTransaction.replace(R.id.frameMain, newPage).commit();
        }
    }

    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공
                isPermission = true;

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // 권한 요청 실패
                isPermission = false;

            }
        };

        TedPermission.with(this.getContext())
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();

    }
}
