package como.isil.mynotes.rest;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.isil.mynotes.rest.R;

import como.isil.mynotes.rest.entity.NoteEntity;
import como.isil.mynotes.rest.storage.db.CRUDOperations;
import como.isil.mynotes.rest.storage.db.MyDatabase;
import como.isil.mynotes.rest.view.dialogs.MyDialogFragment;
import como.isil.mynotes.rest.view.dialogs.MyDialogListener;
import como.isil.mynotes.rest.view.fragments.AddNoteFragment;
import como.isil.mynotes.rest.view.fragments.DetailsFragment;
import como.isil.mynotes.rest.view.listeners.OnNoteListener;

public class NoteActivity extends ActionBarActivity  implements OnNoteListener, MyDialogListener{

    public static final  int ADD_NOTE=100;
    public static final  int DETAIL_NOTE=101;
    public static final  int UPDATE_NOTE=102;
    private static final String TAG ="NoteActivity";

    private AddNoteFragment addNoteFragment= AddNoteFragment.newInstance(null,null);
    private DetailsFragment detailsFragment= DetailsFragment.newInstance(null,null);
    private int fragmentSelected= DETAIL_NOTE;
    private NoteEntity noteEntity;

    private CRUDOperations crudOperations;
    private NoteEntity tmpNoteEntity;

    private View rlayLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        validateExtras();

        rlayLoading= findViewById(R.id.rlayLoading);
        crudOperations= new CRUDOperations(new MyDatabase(this));
        Bundle bundle= new Bundle();
        bundle.putSerializable("NOTE",noteEntity);
        changeFragment(fragmentSelected, bundle);
    }

    private void validateExtras() {
        if(getIntent().getExtras()!=null)
        {
            fragmentSelected= getIntent().getExtras().getInt("FRAGMENT",DETAIL_NOTE);
            noteEntity= (NoteEntity)getIntent().getExtras().getSerializable("NOTE");
        }
    }


    private  void changeFragment(int id,Bundle bundle)
    {
        Fragment fragment= null;
        switch (id)
        {
            case ADD_NOTE:
                fragment=addNoteFragment;
                break;

            case DETAIL_NOTE:
                fragment=detailsFragment;
                break;

            case UPDATE_NOTE:
                fragment=null;
                break;
        }

        if(fragment!=null)
        {
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
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
    public CRUDOperations getCrudOperations() {
        return crudOperations;
    }

    @Override
    public void deleteNote(NoteEntity noteEntity) {
        tmpNoteEntity= noteEntity;
        MyDialogFragment myDialogFragment =new MyDialogFragment();
        Bundle bundle= new Bundle();
        bundle.putString("TITLE","¿Deseas eliminar esta nota?");
        bundle.putInt("TYPE", 100);
        bundle.putSerializable("NOTEENTITY",noteEntity);
        myDialogFragment.setArguments(bundle);
        myDialogFragment.show(getFragmentManager(), "dialog");
    }

    @Override
    public void showParentLoading() {
        this.rlayLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideParentLoading() {
        this.rlayLoading.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void onPositiveListener(Object object, int type) {
        Log.v(TAG, "dialog positive");
        //NoteEntity auxNoteEntity= (NoteEntity)object;
        if(tmpNoteEntity!=null)
        {
            //eliminar
            crudOperations.deleteNote(tmpNoteEntity);
            tmpNoteEntity=null;
            //cerrar vista
            finish();
        }

    }

    @Override
    public void onNegativeListener(Object object, int type) {
        Log.v(TAG, "dialog negative");
    }
}