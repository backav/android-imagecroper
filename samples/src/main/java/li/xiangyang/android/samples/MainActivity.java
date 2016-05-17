package li.xiangyang.android.samples;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import li.xiangyang.android_imagecroper.CropImageActivity;

public class MainActivity extends AppCompatActivity {

    private final int req_takeAvatorPicture = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnTake).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture

                startActivityForResult(intent, req_takeAvatorPicture);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == req_takeAvatorPicture && resultCode == RESULT_OK) {
            Intent intent = new Intent("com.android.camera.action.CROP");

            if (data.getData()!=null){
                intent.setData(data.getData());
            }else{
                intent.putExtra("data",data.getParcelableExtra("data"));
            }
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 640);
            intent.putExtra("outputY", 640);
            intent.putExtra("scale", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.putExtra("return-data", false);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("noFaceDetection", true); // no face detection

            intent.setAction(null);
            intent.setClass(this, CropImageActivity.class);
            startActivityForResult(intent, 3);
        }
    }
}
