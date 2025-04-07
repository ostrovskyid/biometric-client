package dmos.example.biometricclient.di;

import dmos.example.biometricclient.presentation.MainActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, ViewModelModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}