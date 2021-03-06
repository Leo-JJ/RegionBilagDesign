package com.example.jiahua.regionbilagdesign.Fragmenter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jiahua.regionbilagdesign.Logic.Fragmentmanager;
import com.example.jiahua.regionbilagdesign.R;
import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Jiahua on 27-09-2016.
 */
public class Laegeunderskrift_fragment extends Fragment {

    private SignaturePad mSignaturePad;
    private ImageButton mClearButton;
    private ImageButton mSaveButton;
    //private Excelbilag_logik excel = new Excelbilag_logik();
    private Uri contentUri;
    //private bilagobjekt bilagindholdet;
    private Fragmentmanager fragments = new Fragmentmanager();
    public static boolean bilagsendt = false;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.laegensunderskrift_view, container, false);

        mSignaturePad = (SignaturePad) rod.findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {

            @Override
            public void onStartSigning() {
                Toast.makeText(getActivity(), "Lægen Underskriver", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });

        mClearButton = (ImageButton) rod.findViewById(R.id.provigen);
        mSaveButton = (ImageButton) rod.findViewById(R.id.send);

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                if (addSignatureToGallery(signatureBitmap)) {
                    if (Laegeunderskrift_fragment.bilagsendt) {
                        Toast.makeText(getActivity(), "Bilag Sendt, du får en bekræftelse snarest på mail ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Der skete en fejl, brug venligst et almindeligt bilag ", Toast.LENGTH_LONG).show();
                    }

                }
                getFragmentManager().beginTransaction().replace(R.id.container, fragments.getTolkensunderskriftfragment()).addToBackStack(fragments.getTolkensunderskriftfragment().getTag()).commit();

            }
        });

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }

        if (getArguments() != null) {
            //bilagindholdet = (bilagobjekt) getArguments().getSerializable("bilagindholdet");
        }
        return rod;
    }


    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs() && !file.isDirectory()) {
            Log.e("signature", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
        stream.close();
    }

    public boolean addSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("Underskrift Tolkdanmark"), String.format("Underskrift_%d.png", System.currentTimeMillis()));
            saveBitmapToJPG(signature, photo);
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            contentUri = Uri.fromFile(photo);
            mediaScanIntent.setData(contentUri);
            getActivity().sendBroadcast(mediaScanIntent);
            result = true;
            //excel.savetoexcel(photo,getContext(),bilagindholdet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}