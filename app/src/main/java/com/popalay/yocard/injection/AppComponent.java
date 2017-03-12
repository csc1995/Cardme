package com.popalay.yocard.injection;

import com.popalay.yocard.ui.addcard.AddCardPresenter;
import com.popalay.yocard.ui.cards.CardsPresenter;
import com.popalay.yocard.ui.holdercards.HolderCardsPresenter;
import com.popalay.yocard.ui.holders.HoldersPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class
})
public interface AppComponent {

    void inject(AddCardPresenter presenter);

    void inject(CardsPresenter presenter);

    void inject(HoldersPresenter presenter);

    void inject(HolderCardsPresenter presenter);
}