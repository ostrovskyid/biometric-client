package dmos.example.biometricclient.di;

import android.app.Application;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dmytro.o.github.BiometricSdkInterface;
import dmytro.o.github.MockBiometricSdk;

@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public BiometricSdkInterface provideBiometricSdk() {
        return new MockBiometricSdk();
    }
}
