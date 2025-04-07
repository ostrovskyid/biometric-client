package dmos.example.biometricclient.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dmos.example.biometricclient.presentation.MainViewModel;


@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(DaggerViewModelFactory factory);
}