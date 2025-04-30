package org.sopt.at.domain.repository

interface HomeRepository {
    suspend fun getHomeBanners(): Pair<List<Pair<String, String>>, List<List<String>>>
}
