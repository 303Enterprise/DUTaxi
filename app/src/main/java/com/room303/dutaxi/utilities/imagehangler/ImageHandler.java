package com.room303.dutaxi.utilities.imagehangler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageHandler {
    // least - 0, best - 100
    private static Bitmap compressBitmap(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int resultQualityPercentage) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, resultQualityPercentage, out);
        Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
        return decoded;
    }

    private static Bitmap cutBitmap(Bitmap sourceBitmap) {
        int width = sourceBitmap.getWidth();
        int height = sourceBitmap.getHeight();

        if (width > height) {
            return Bitmap.createBitmap(sourceBitmap, (width - height) / 2, 0, height, height);
        } else {
            return Bitmap.createBitmap(sourceBitmap, 0, (height - width) / 2, width, width);
        }
    }

    // MediaMetadataCompat
    private Bitmap scaleBitmap(Bitmap bitmap, int maxSize) {
        float maxSizeF = maxSize;
        float widthScale = maxSizeF / bitmap.getWidth();
        float heightScale = maxSizeF / bitmap.getHeight();
        float scale = Math.min(widthScale, heightScale);
        int height = (int) (bitmap.getHeight() * scale);
        int width = (int) (bitmap.getWidth() * scale);
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    String currentPhotoPath;

    private File createImageFile(Activity activity) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private static final int REQUEST_IMAGE_CAPTURE = 123;

    private void dispatchTakePictureIntent(Activity activity) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(activity);
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(activity,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }
}
