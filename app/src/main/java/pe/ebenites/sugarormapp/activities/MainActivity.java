package pe.ebenites.sugarormapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import pe.ebenites.sugarormapp.R;
import pe.ebenites.sugarormapp.adapters.NoteAdapter;
import pe.ebenites.sugarormapp.models.Note;
import pe.ebenites.sugarormapp.repositories.NoteRepository;

public class  MainActivity extends AppCompatActivity {

    private TextView fullnameText;
    private DrawerLayout drawerLayout;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REGISTER_FORM_REQUEST = 100;
    private RecyclerView notesList;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Toast.makeText(MainActivity.this, "Go home...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_calendar:
                        Toast.makeText(MainActivity.this, "Go calendar...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_gallery:
                        Toast.makeText(MainActivity.this, "Go gallery...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_locations:
                        Toast.makeText(MainActivity.this, "Go locations...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_settings:
                        Toast.makeText(MainActivity.this, "Go Settings...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_logout:
                        CallLogin();
                        Toast.makeText(MainActivity.this, "Do logout", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        this.notesList = (RecyclerView)findViewById(R.id.notes_list);
        this.notesList.setLayoutManager(new LinearLayoutManager(this));
        this.notesList.setAdapter(new NoteAdapter());
        refreshData();
    }

    private void refreshData() {
        NoteAdapter adapter = (NoteAdapter)notesList.getAdapter();
        List<Note> notes= NoteRepository.getNotes();
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();//refresca los cambios en el RV
    }
    public void CallLogin(){
        startActivity(new Intent(this,LoginActivity.class));

        finish();
    }
    public void showRegistrer(View view){
        startActivityForResult(new Intent(this,RegistrerActivityNote.class),REGISTER_FORM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REGISTER_FORM_REQUEST:
                if (resultCode == RESULT_OK){
                    refreshData();
                }
                break;
                default:
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: //Option open drawer
                if(!drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.openDrawer(GravityCompat.START);
                else
                    drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
