package com.ghroosk.practice.picasso;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.ghroosk.practice.picasso.transform.CropCircleTransformation;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String path = getResourcesUri(R.drawable.pic_network_error);
        Log.e(TAG, "onCreate path: " + path);
        Uri uri = Uri.parse(path);
//        android.resource://com.ghroosk.practice.picasso/drawable/pic_network_error
        mImageView = findViewById(R.id.image);
        Picasso.get()
                .load(uri)
                .placeholder(R.drawable.pic_network_error)
                .fit()
                .error(R.drawable.pic_network_error)
                .rotate(180)
                .priority(Picasso.Priority.HIGH)
//                .memoryPolicy()

                .transform(new CropCircleTransformation())
                .into(mImageView);

        Picasso picasso = Picasso.get();
        picasso.pauseTag(1);
        picasso.setLoggingEnabled(true);

        try {
            Bitmap bitmap = Picasso.get()
                    .load(uri)
                    .placeholder(R.drawable.pic_network_error)
                    .fit()
                    .error(R.drawable.pic_network_error)
                    .rotate(180)
                    .priority(Picasso.Priority.HIGH)
                    .transform(new CropCircleTransformation())
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getResourcesUri(@DrawableRes int id) {
        Resources resources = getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        return uriPath;
    }


}
