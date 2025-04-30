package org.sopt.at.data.repositoryimpl

import org.sopt.at.data.datasource.HomeDataSource
import org.sopt.at.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource,
) : HomeRepository {
    override suspend fun getHomeBanners(): Pair<List<Pair<String, String>>, List<List<String>>> =
        Pair(
            homeDataSource.getHomeSwipeBanners(),
            homeDataSource.getHomeSmallBanners()
        )
}
