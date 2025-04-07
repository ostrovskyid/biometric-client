package dmos.example.biometricclient.nativ;

import javax.inject.Inject;

public class SecurityNative {
    static {
        System.loadLibrary("security-lib");
    }

    @Inject
    public SecurityNative() {
        // Empty constructor; Dagger can now construct SecurityNative
    }
    // Native method to simulate encryption.
    public native String encryptData(String data);

    public String getEncryptedData(String data) {
        return encryptData(data);
    }
}