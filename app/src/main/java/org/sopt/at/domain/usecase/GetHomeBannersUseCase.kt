package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeBannersUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
) {
    suspend operator fun invoke(): Pair<List<Pair<String, String>>, List<List<String>>> =
        homeRepository.getHomeBanners()
}
