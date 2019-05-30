package pe.ebenites.sugarormapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import pe.ebenites.sugarormapp.R;
import pe.ebenites.sugarormapp.models.Note;
import pe.ebenites.sugarormapp.repositories.NoteRepository;

public class RegistrerActivityNote extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText titleInput;
    private EditText contentInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer_note);

        titleInput = (EditText) findViewById(R.id.title_input);
        contentInput = (EditText) findViewById(R.id.content_input);

    }

    public void callRegistrer(View view) {
        String titulo = titleInput.getText().toString();
        String content = contentInput.getText().toString();
        if (titulo.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Debe completar todo los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        Note note = new Note();
        note.setTitle(titulo);
        note.setContent(content);
        note.setDate(new Date());
        note.setFavorite(false);

        NoteRepository.add(note);
        Toast.makeText(this, "Nota registrada correctamente", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();

    }
}