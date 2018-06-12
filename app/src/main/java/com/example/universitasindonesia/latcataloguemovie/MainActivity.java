package com.example.universitasindonesia.latcataloguemovie;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<ModelMovie>> {
    ListView listView;
    Button btnCari;
    ImageView imgposter;
    EditText editjudul;

    static final String EXTRAS_MOVIES = "EXTRAS_MOVIES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.lvFilem);

        editjudul = (EditText)findViewById(R.id.editjudul);
        imgposter = (ImageView)findViewById(R.id.imgfilm);
        btnCari = (Button)findViewById(R.id.btn_cari);

        String judul_film = editjudul.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIES, judul_film);

        getLoaderManager().initLoader(0, bundle, this);
    }

    @Override
    public Loader<ArrayList<ModelMovie>> onCreateLoader(int i, Bundle bundle)
    {
        String judulfilm = "";
        if(bundle != null)
        {
            judulfilm = bundle.getString(EXTRAS_MOVIES);
        }
        return new MovieAsyncronus(this, judulfilm);
    }


    View.OnClickListener movieListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String judul_movie = editjudul.getText().toString();
            if(TextUtils.isEmpty(judul_movie)){
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIES, judul_movie);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}
