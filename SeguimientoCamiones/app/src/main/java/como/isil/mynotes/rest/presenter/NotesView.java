package como.isil.mynotes.rest.presenter;

import android.content.Context;

import como.isil.mynotes.rest.entity.NoteEntity;

import java.util.List;

/**
 * Created by em on 8/06/16.
 */
public interface NotesView {

    void showLoading();
    void hideLoading();
    Context getContext();

    void onMessageError(String message);
    void renderNotes(List<NoteEntity> notes);

}