package dmos.example.biometricclient.presentation;

import android.app.Application;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import javax.inject.Inject;
import dmytro.o.github.BiometricSdkInterface;
import dmytro.o.github.BiometricResult;
import dmos.example.biometricclient.nativ.SecurityNative;
import dmytro.o.github.ImageWrapper;


public class MainViewModel extends AndroidViewModel {

    private final BiometricSdkInterface biometricSdk;
    private final SecurityNative securityNative;
    private final MutableLiveData<String> resultText = new MutableLiveData<>();
    private BiometricResult currentResult;

    @Inject
    public MainViewModel(@NonNull Application application,
                         BiometricSdkInterface biometricSdk,
                         SecurityNative securityNative) {
        super(application);
        this.biometricSdk = biometricSdk;
        this.securityNative = securityNative;
    }

    public LiveData<String> getResultText() {
        return resultText;
    }

    public void scanDocument( ImageWrapper dummyDocument) {
        currentResult = biometricSdk.scanIdentityDocument(dummyDocument);
        resultText.setValue(currentResult.message());
    }

    public void authenticateUser(ImageWrapper dummyFace) {
        currentResult = biometricSdk.authenticateUser(dummyFace);
        resultText.setValue(currentResult.message());
    }

    public void submitResult() {
        if (currentResult != null) {
            String encrypted = securityNative.getEncryptedData(currentResult.message());
            resultText.setValue("Native encrypted: " + encrypted);
        } else {
            resultText.setValue("No biometric result to submit.");
        }
    }
}