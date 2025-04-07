package dmos.example.biometricclient.presentation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dmytro.o.github.BiometricSdkInterface;
import dmytro.o.github.BiometricResult;
import dmytro.o.github.ImageWrapper;
import dmos.example.biometricclient.nativ.SecurityNative;
import dmos.example.biometricclient.presentation.MainViewModel;
import dmos.example.biometricclient.presentation.helpers.BitmapWrapper;

// Dummy implementation of the biometric SDK interface for testing.
class DummyBiometricSdk implements BiometricSdkInterface {
    @Override
    public BiometricResult scanIdentityDocument(ImageWrapper documentImage) {
        return new BiometricResult(true, "Dummy scan result");
    }

    @Override
    public BiometricResult authenticateUser(ImageWrapper faceImage) {
        return new BiometricResult(true, "Dummy auth result");
    }
}

// Dummy implementation of SecurityNative that bypasses the native call.
class DummySecurityNative extends SecurityNative {
    @Override
    public String encryptData(String data) {
        return "dummy_encrypted_" + data;
    }
}

// Minimal dummy Application subclass for testing.
class DummyApplication extends Application {
}

public class MainViewModelTest {

    private MainViewModel viewModel;

    @BeforeEach
    public void setUp() {
        Application app = new DummyApplication();
        BiometricSdkInterface dummySdk = new DummyBiometricSdk();
        SecurityNative dummySecurity = new DummySecurityNative();
        viewModel = new MainViewModel(app, dummySdk, dummySecurity);
    }

    @Test
    public void testScanDocument() {
        // Create a dummy Bitmap (10x10 pixels)
        Bitmap dummyBitmap = Bitmap.createBitmap(10, 10, Config.ARGB_8888);
        // Wrap it in a BitmapWrapper (which implements ImageWrapper)
        ImageWrapper wrapper = new BitmapWrapper(dummyBitmap);
        viewModel.scanDocument(wrapper);
        // Verify that the dummy scan result is set.
        assertTrue(viewModel.getResultText().getValue().contains("Dummy scan result"));
        assertEquals("Dummy scan result", viewModel.getResultText().getValue());
    }

    @Test
    public void testAuthenticateUser() {
        Bitmap dummyBitmap = Bitmap.createBitmap(10, 10, Config.ARGB_8888);
        ImageWrapper wrapper = new BitmapWrapper(dummyBitmap);
        viewModel.authenticateUser(wrapper);
        assertTrue(viewModel.getResultText().getValue().contains("Dummy auth result"));
        assertEquals("Dummy auth result", viewModel.getResultText().getValue());
    }

    @Test
    public void testSubmitResult() {
        Bitmap dummyBitmap = Bitmap.createBitmap(10, 10, Config.ARGB_8888);
        ImageWrapper wrapper = new BitmapWrapper(dummyBitmap);
        // Set the current result by scanning.
        viewModel.scanDocument(wrapper);
        // Then, submit the result to obtain the encrypted string.
        viewModel.submitResult();
        assertEquals("Native encrypted: dummy_encrypted_Dummy scan result", viewModel.getResultText().getValue());
    }
}
