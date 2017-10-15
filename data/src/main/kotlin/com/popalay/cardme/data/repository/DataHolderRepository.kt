package com.popalay.cardme.data.repository

import com.popalay.cardme.data.applyMapper
import com.popalay.cardme.data.dao.HolderDao
import com.popalay.cardme.domain.model.Holder
import com.popalay.cardme.domain.repository.HolderRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataHolderRepository @Inject constructor(
        private val holderDao: HolderDao
) : HolderRepository {

    override fun save(holder: Holder): Completable = Completable.fromAction {
        holderDao.insertOrUpdate(holder.applyMapper())
    }

    override fun getAll(): Flowable<List<Holder>> = holderDao.getAllNotTrashed()

    override fun get(holderName: String): Flowable<Holder> = holderDao.get(holderName)

    override fun removeTrashed(): Completable = Completable.complete()/*RxRealm.doTransactional {
        it.where(Holder::class.java).equalTo(DataHolder.IS_TRASH, true).findAll().deleteAllFromRealm()
    }*/

    private fun updateTrashFlag() {
/*        val intoTrash = realm.where(DataHolder::class.java)
                //TODO.isEmpty(DataHolder.CARDS)
                .isEmpty(DataHolder.DEBTS)
                .findAll()

        for (item in intoTrash) {
            item.isTrash = true
        }

        val fromTrash = realm.where(DataHolder::class.java)
                //TODO.isNotEmpty(DataHolder.CARDS)
                .or()
                .isNotEmpty(DataHolder.DEBTS)
                .findAll()

        for (item in fromTrash) {
            item.isTrash = false
        }*/
    }
}