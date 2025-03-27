package com.alangeronimo.koin_exercise.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class SimpleWorker(
    private val simpleService: SimpleService,
    appContext: Context,
    private val params: WorkerParameters
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return Result.success()
    }
}