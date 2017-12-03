package sousajunior.devfest.firestore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import sousajunior.devfest.firestore.Model.Filme;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private FirebaseFirestore mFirestore;

    EditText etComentario;
    EditText etFilme;

    TextView tvComentario;
    TextView tvFilme;

    String filme;
    String comentario;

    Button btPublicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFirestore();
        readFirestore();
    }

    private void initFirestore() {

        etComentario = (EditText) findViewById(R.id.etComentario);
        etFilme = (EditText) findViewById(R.id.etFilme);

        tvComentario = (TextView) findViewById(R.id.tvComentario);
        tvFilme = (TextView) findViewById(R.id.tvFilme );

        btPublicar = (Button) findViewById(R.id.btPublish);

        btPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFirestore();
            }
        });

        mFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        readFirestore();
    }

    private void readFirestore(){

        DocumentReference readData = mFirestore.collection("filmes").document("novoTeste");
        readData.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()) {
                            Filme fv = documentSnapshot.toObject(Filme.class);
                            tvFilme.setText(fv.getTitulo());
                            tvComentario.setText(fv.getComentario());
                        }
                    }
                });
    }

    private void writeFirestore(){
        // Create a new user with a first and last name
        filme = etFilme.getText().toString();
        comentario = etComentario.getText().toString();

        Filme filmeFireStore = new Filme(filme, comentario);

        DocumentReference writeData = mFirestore.collection("filmes").document("novoTeste");

        writeData.set(filmeFireStore).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Sucesso", Toast.LENGTH_SHORT).show();
                readFirestore();
            }
        });

    }
}
