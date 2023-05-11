package com.amg.run.repositories

import com.amg.run.db.Run
import com.amg.run.db.RunDao
//import com.example.run.db.Run
//import com.example.run.db.RunDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDao: RunDao
) {
    suspend fun insertRun(run: Run) = runDao.insertRun(run)
    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunSortedByDate() = runDao.getAllRunSortedByDate()
    fun getAllRunSortedByDistance() = runDao.getAllRunSortedByDistance()
    fun getAllRunSortedByTimeInMillis() = runDao.getAllRunSortedByTimeInMillis()
    fun getAllRunSortedByCaloriesBurned() = runDao.getAllRunSortedByCaloriesBurned()
    fun getAllRunSortedByavgSpeed() = runDao.getAllRunSortedByavgSpeed()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()
    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()
    fun getTotalDistance() = runDao.getTotalDistance()
    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()





}