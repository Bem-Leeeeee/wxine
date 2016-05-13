package com.wxine.android;

import android.content.ContentResolver;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by zz on 2016/4/22.
 */
public class PersonalDataRealname_Tab extends Fragment {
    Button PersonalRealnameUpdata;
    Button SelectFile;
    View view;
    private LayoutInflater mInflater;
    private LinearLayout mGallery;
    private Bitmap[] mImgIds;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(getContext());
        view = inflater.inflate(R.layout.personaldata_realname_tab, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        PersonalRealnameUpdata = (Button) view.findViewById(R.id.PersonalRealnameUpdata);
        PersonalRealnameUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "上传已确认", Toast.LENGTH_LONG).show();
            }
        });

        SelectFile = (Button) view.findViewById(R.id.SelectFile);
        SelectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(Intent.ACTION_PICK, null);
                int1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(int1, 1);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //图库

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();


            //显示在Gallery视图
            initData(BitmapFactory.decodeFile(picturePath));
            initView();

        super.onActivityResult(requestCode, resultCode, data);
    }
    //照片的显示
    private void initData(Bitmap per_photos) {
        mImgIds = new Bitmap[]{per_photos};
    }

    private void initView() {
        mGallery = (LinearLayout) view.findViewById(R.id.id_gallery);
        for (int i = 0; i < mImgIds.length; i++) {
            final View personalphoto_item = mInflater.inflate(R.layout.personalphoto_item,
                    mGallery, false);
            final ImageView img = (ImageView) personalphoto_item.findViewById(R.id.per_photos);
            img.setImageBitmap(mImgIds[i]);
            mGallery.addView(personalphoto_item);
            //删除照片
            ImageView remove = (ImageView) personalphoto_item.findViewById(R.id.remove_perimg);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGallery.removeView(personalphoto_item);
                }
            });
        }
    }
}
