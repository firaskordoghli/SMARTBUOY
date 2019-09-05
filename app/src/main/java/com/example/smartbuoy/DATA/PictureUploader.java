package com.example.smartbuoy.DATA;

import android.util.Log;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.Map;

public class PictureUploader extends Thread{

    private static final String TAG = "PictureUploader";

    @Override    public void run() {

        Cloudinary cloudinary = new Cloudinary("cloudinary://...");
        try {
            Map x = cloudinary.uploader().upload("/storage/emulated/0/Pictures/smilebypro/flashair_b86b23c647d4/DCIM/101AZ421/101_0072.JPG", ObjectUtils.emptyMap());
            Log.i(TAG, "taille du retour  = "+ String.valueOf(x.size()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startUpload() {
        this.start(); // -> thread is alive !
         }


    }