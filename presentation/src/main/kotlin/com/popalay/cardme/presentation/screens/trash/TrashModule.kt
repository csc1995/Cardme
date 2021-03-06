package com.popalay.cardme.presentation.screens.trash

import android.arch.lifecycle.ViewModel
import com.popalay.cardme.injection.PerActivity
import com.popalay.cardme.injection.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TrashModule {

    @Binds
    @IntoMap
    @PerActivity
    @ViewModelKey(TrashViewModel::class)
    abstract fun bindsTrashViewModel(viewModel: TrashViewModel): ViewModel
}