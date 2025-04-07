package dmos.example.biometricclient.presentation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import javax.inject.Inject;
import dmos.example.biometricclient.R;
import dmos.example.biometricclient.di.AppComponent;
import dmos.example.biometricclient.di.AppModule;
import dmos.example.biometricclient.di.DaggerAppComponent;
import dmos.example.biometricclient.presentation.helpers.BitmapWrapper;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MainViewModel mainViewModel;
    private TextView txtResult;
    private Button btnScanDocument, btnAuthenticateUser, btnSubmitResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
                .build();
        appComponent.inject(this);

        mainViewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);

        txtResult = findViewById(R.id.txtResult);
        btnScanDocument = findViewById(R.id.btnScanDocument);
        btnAuthenticateUser = findViewById(R.id.btnAuthenticateUser);
        btnSubmitResult = findViewById(R.id.btnSubmitResult);

        mainViewModel.getResultText().observe(this, txtResult::setText);

        btnScanDocument.setOnClickListener(v -> {
            Bitmap dummyDocument = BitmapFactory.decodeResource(getResources(), R.drawable.placeholder_document);
            mainViewModel.scanDocument(new BitmapWrapper(dummyDocument));
        });

        btnAuthenticateUser.setOnClickListener(v -> {
            Bitmap dummyFace = BitmapFactory.decodeResource(getResources(), R.drawable.placeholder_face);
            mainViewModel.authenticateUser(new BitmapWrapper(dummyFace));
        });

        btnSubmitResult.setOnClickListener(v -> mainViewModel.submitResult());
    }
}