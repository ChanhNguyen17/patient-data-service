package patientdataservice.domain.services

import arrow.core.Either
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import patientdataservice.application.models.PatientDataRequest
import patientdataservice.domain.ApplicationError
import patientdataservice.domain.models.PatientData
import patientdataservice.domain.repositories.PatientDataRepository
import java.util.UUID

@Service
@Transactional
class PatientDataService(
    private val patientDataRepository: PatientDataRepository
) {
    fun getPatientDataById(patientId: UUID): List<PatientData> {
        return patientDataRepository.getPatientDataById(patientId)
    }

    fun addPatientData(patientDataRequest: PatientDataRequest, patientId: UUID): Either<ApplicationError, UUID> {
        val id = patientDataRepository.addPatientData(patientDataRequest, patientId)
        return Either.Right(id)
    }
}
