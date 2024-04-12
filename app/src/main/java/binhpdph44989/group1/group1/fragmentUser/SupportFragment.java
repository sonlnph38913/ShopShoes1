package binhpdph44989.group1.group1.fragmentUser;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import binhpdph44989.group1.group1.R;


public class SupportFragment extends Fragment {
        TextView txtGoogle,txtFace,txtYoutube,txtInsta;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_support, container, false);
        txtGoogle = view.findViewById(R.id.txtGoogle);
        txtFace = view.findViewById(R.id.txtFacebook);
        txtYoutube = view.findViewById(R.id.txtYoutube);
        txtInsta = view.findViewById(R.id.txtInstagram);

        txtGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://sneakerdaily.vn/");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.google.android");
                try {
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://sneakerdaily.vn/")));
                }
            }
        });
        txtFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTofacebook("865378794956831");
            }
        });
        txtYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.youtube.com/@SneakerDailyVN");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.youtube.android");
                try {
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/@SneakerDailyVN")));
                }
            }
        });
        txtInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/sneakernews/");
          Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.instagram.android");
            try {
                startActivity(intent);
            }catch (ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/sneakernews/")));
            }
            }
        });
        return view;
    }
    private void goTofacebook(String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + id));
            startActivity(intent);

        } catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ww.facebook.com/" + id));
            startActivity(intent);
        }
    }
}