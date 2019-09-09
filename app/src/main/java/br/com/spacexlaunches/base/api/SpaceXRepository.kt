package br.com.spacexlaunches.base.api

import br.com.spacexlaunches.base.api.SpaceXApi
import br.com.spacexlaunches.base.api.models.Launch
import javax.inject.Inject

class SpaceXRepository @Inject constructor(
    private val api: SpaceXApi
) {

    suspend fun getAllLaunches(): List<Launch> {
        return api.getAllLaunches()
    }

}
