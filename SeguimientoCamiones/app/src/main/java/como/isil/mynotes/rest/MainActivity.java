package como.isil.mynotes.rest;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.isil.mynotes.rest.R;

import como.isil.mynotes.rest.entity.NoteEntity;
import como.isil.mynotes.rest.presenter.NotesPresenter;
import como.isil.mynotes.rest.presenter.NotesView;
import como.isil.mynotes.rest.storage.PreferencesHelper;
import como.isil.mynotes.rest.storage.db.CRUDOperations;
import como.isil.mynotes.rest.storage.db.MyDatabase;
import como.isil.mynotes.rest.utils.CapitalizeString;
import como.isil.mynotes.rest.view.adapters.NoteAdapter;

import java.util.List;


public class MainActivity extends ActionBarActivity implements NotesView {

    private static final String TAG ="MainActivity" ;
    private static final int ACTION_ADD=1;
    private static final int ACTION_DETAIL=2;

    private TextView tviLogout,tviUser;
    private ListView lstNotes;
    private Button btnAddNote;
    private View rlayLoading,container;
    private List<NoteEntity> lsNoteEntities;
    private CRUDOperations crudOperations;
    private NoteAdapter noteAdapter;

    private NotesPresenter notesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //populate();
        notesPresenter= new NotesPresenter();
        notesPresenter.attachedView(this);
        init();
        //loadData();
        //loadCloud();
    }

    private void loadCloud() {
        notesPresenter.loadNotes();
    }

    private void loadData() {
        crudOperations= new CRUDOperations(new MyDatabase(this));
        lsNoteEntities= crudOperations.getAllNotes();
        noteAdapter= new NoteAdapter(this,lsNoteEntities);
        lstNotes.setAdapter(noteAdapter);


    }

    private void populate() {

        CRUDOperations crudOperations= new CRUDOperations(new MyDatabase(this));
        crudOperations.addNote(new NoteEntity("Mi Nota","Esta es un nota ",null));
        crudOperations.addNote(new NoteEntity("Segunda Nota","Esta es la segunds nota ",null));
        crudOperations.addNote(new NoteEntity("Tercera Nota","Esta es la tercera nota ",null));
        crudOperations.addNote(new NoteEntity("Cuarta Nota","Esta es la cuarta nota ",null));
        crudOperations.addNote(new NoteEntity("Quinta Nota","Esta es la quinta nota ",null));
        crudOperations.addNote(new NoteEntity("Sexta Nota","Esta es la sexta nota ",null));

        Log.v(TAG, "populate " + crudOperations.getAllNotes());
    }

    private void init() {
        tviLogout= (TextView)findViewById(R.id.tviLogout);
        tviUser= (TextView)findViewById(R.id.tviUser);
        lstNotes= (ListView)(findViewById(R.id.lstNotes));
        btnAddNote= (Button)(findViewById(R.id.btnAddNote));
        rlayLoading= (findViewById(R.id.rlayLoading));

        //user Info
        String username = PreferencesHelper.getUserSession(this);
        if(username!=null)
        {
            tviUser.setText("Bienvenido "+ new CapitalizeString(username).first());
        }

        //events
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNote(ACTION_ADD, null);
            }
        });

        lstNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NoteEntity noteEntity = (NoteEntity) adapterView.getAdapter().getItem(i);
                gotoNote(ACTION_DETAIL, noteEntity);
            }
        });

        tviLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void gotoNote(int action, NoteEntity noteEntity) {
        Intent intent= new Intent(this,NoteActivity.class);

        switch (action)
        {
            case ACTION_ADD:
                    intent.putExtra("FRAGMENT",NoteActivity.ADD_NOTE);
                    startActivity(intent);
                break;
            case ACTION_DETAIL:
                intent.putExtra("FRAGMENT",NoteActivity.DETAIL_NOTE);
                intent.putExtra("NOTE", noteEntity);
                startActivity(intent);
                break;
        }
    }

    private void logout() {
        PreferencesHelper.signOut(this);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResumen");
        //loadData();
        loadCloud();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public void showLoading() {
        this.rlayLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.rlayLoading.setVisibility(View.GONE);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onMessageError(String message) {
        Snackbar snackbar = Snackbar
                .make(container,message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    public void renderNotes(List<NoteEntity> notes) {
        lsNoteEntities= notes;
        noteAdapter= new NoteAdapter(this,lsNoteEntities);
        lstNotes.setAdapter(noteAdapter);
    }
}
