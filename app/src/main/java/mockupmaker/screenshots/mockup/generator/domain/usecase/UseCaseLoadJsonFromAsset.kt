package mockupmaker.screenshots.mockup.generator.domain.usecase

import mockupmaker.screenshots.mockup.generator.core.Resource
import mockupmaker.screenshots.mockup.generator.core.UseCase
import mockupmaker.screenshots.mockup.generator.data.model.DeviceFrameItem
import mockupmaker.screenshots.mockup.generator.domain.DeviceFrameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseLoadJsonFromAsset @Inject constructor(private val deviceFrameRepository: DeviceFrameRepository) :
    UseCase<Flow<Resource<List<DeviceFrameItem>>>, Unit>() {
    override suspend fun action(params: Unit) = deviceFrameRepository.loadJSONFromAsset()
}