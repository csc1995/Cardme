package com.popalay.cardme.data.repository

import com.google.gson.Gson
import com.popalay.cardme.data.dao.CardDao
import com.popalay.cardme.data.toData
import com.popalay.cardme.data.toDomain
import com.popalay.cardme.domain.model.Card
import com.popalay.cardme.domain.repository.CardRepository
import dagger.Reusable
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class CardRepository @Inject constructor(
        private val cardDao: CardDao,
        private val gson: Gson
) : CardRepository {

    override fun save(data: Card): Completable = Completable.fromAction {
        cardDao.insertOrUpdate(data.toData())
    }

    override fun get(id: String): Flowable<Card> = cardDao.get(id)
            .map { it.toDomain() }

    override fun update(data: List<Card>): Completable = Completable.fromAction {
        cardDao.updateAll(data.map { it.toData() })
    }

    override fun getAll(): Flowable<List<Card>> = cardDao.getAll()
            .map { it.map { it.toDomain() } }

    override fun getAllTrashed(): Flowable<List<Card>> = cardDao.getAllTrashed()
            .map { it.map { it.toDomain() } }

    override fun getAllNotTrashed(): Flowable<List<Card>> = cardDao.getAllNotTrashed()
            .map { it.map { it.toDomain() } }

    override fun markAsTrash(data: Card): Completable = Completable.fromAction {
        cardDao.insertOrUpdate(data.copy(isTrash = true).toData())
    }

    override fun removeTrashed(): Completable = cardDao.getAllTrashed()
            .flatMapCompletable { Completable.fromAction { cardDao.deleteAll(it) } }

    override fun restore(data: Card): Completable = Completable.fromAction {
        cardDao.insertOrUpdate(data.copy(isTrash = true).toData())
    }

    override fun contains(id: String): Single<Boolean> = cardDao.getCount(id).map { it > 0 }

    override fun toJson(card: Card): Single<String> = Single.fromCallable { gson.toJson(card) }

    override fun fromJson(source: String): Single<Card> = Single.fromCallable {
        gson.fromJson<Card>(source, Card::class.java)
    }

    override fun getNotTrashedByHolder(holderName: String): Flowable<List<Card>>
            = cardDao.getNotTrashedByHolder(holderName)
            .map { it.map { it.toDomain() } }
}