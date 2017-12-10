package com.popalay.cardme.data.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.popalay.cardme.data.model.Debt
import io.reactivex.Flowable

@Dao
interface DebtDao {

    @Query("SELECT * FROM debts " +
            "WHERE id = :id " +
            "LIMIT 1")
    fun get(id: Int): Flowable<Debt>

    @Query("SELECT * FROM debts " +
            "ORDER BY createdAt")
    fun getAll(): Flowable<List<Debt>>

    @Query("SELECT * FROM debts " +
            "WHERE isTrash = 0 " +
            "ORDER BY createdAt")
    fun getAllNotTrashed(): Flowable<List<Debt>>

    @Query("SELECT * FROM debts " +
            "WHERE isTrash = 1 " +
            "ORDER BY createdAt")
    fun getAllTrashed(): Flowable<List<Debt>>

    @Query("SELECT * FROM debts " +
            "WHERE isTrash = 0 AND holderName = :holderName " +
            "ORDER BY createdAt")
    fun getNotTrashedByHolder(holderName: String): Flowable<List<Debt>>

    @Query("SELECT COUNT (*) FROM debts " +
            "WHERE isTrash = 0 AND holderName = :holderName")
    fun getCountByHolder(holderName: String): Flowable<Int>

    @Insert(onConflict = REPLACE)
    fun insertOrUpdate(debt: Debt)

    @Update(onConflict = REPLACE)
    fun updateAll(debts: List<Debt>)

    @Delete
    fun deleteAll(debts: List<Debt>)

    @Delete
    fun delete(debt: Debt)

}