package com.example.recordingapp.energy_score

object FakeDatabase {
    fun getEScoreData(): List<EnergyScoreFactorModel> {
        val eScoreFactorsList = listOf(
            EnergyScoreFactorModel(
                title = "Sleep time average",
                score = 0.9f,
                tag = "Excellent"
            ),
            EnergyScoreFactorModel(
                title = "Sleep time consistency",
                score = 0.8f,
                tag = "Excellent"
            ),
            EnergyScoreFactorModel(
                title = "Bed/wake time consistency",
                score = 0.85f,
                tag = "Excellent"
            ),EnergyScoreFactorModel(
                title = "Sleep timing",
                score = 0.75f,
                tag = "Excellent"
            ),EnergyScoreFactorModel(
                title = "Previous day activity",
                score = 0.97f,
                tag = "Excellent"
            ),EnergyScoreFactorModel(
                title = "Activity consistency",
                score = 0.82f,
                tag = "Excellent"
            ),EnergyScoreFactorModel(
                title = "Sleeping HR",
                score = 0.90f,
                tag = "Excellent"
            ),EnergyScoreFactorModel(
                title = "Sleeping HRV",
                score = 1f,
                tag = "Excellent"
            ),
        )
        return eScoreFactorsList
    }
}