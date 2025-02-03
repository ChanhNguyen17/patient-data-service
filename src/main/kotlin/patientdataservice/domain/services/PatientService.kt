package patientdataservice.domain.services

import arrow.core.Either
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import patientdataservice.application.models.PatientRequest
import patientdataservice.application.models.PatientView
import patientdataservice.domain.ApplicationError
import patientdataservice.domain.PatientNotFound
import patientdataservice.domain.repositories.PatientRepository
import java.util.UUID

@Service
@Transactional
class PatientService(
    private val patientRepository: PatientRepository,
    private val patientDataService: PatientDataService
) {
    fun getAllPatients(): List<PatientView> {
        val patients = patientRepository.getAllPatients()
        return patients.map { patient ->
            val patientData = patientDataService.getPatientDataById(patient.patientId)
            PatientView(
                patientId = patient.patientId,
                name = patient.name,
                data = patientData
            )
        }
    }

    fun getPatientById(patientId: UUID): Either<ApplicationError, PatientView> {
        val patient = patientRepository.getPatientById(patientId)
            ?: return Either.Left(PatientNotFound(patientId))

        val patientData = patientDataService.getPatientDataById(patientId)

        return Either.Right(
            PatientView(
                patientId = patient.patientId,
                name = patient.name,
                data = patientData
            )
        )
    }

    fun addPatient(patient: PatientRequest): Either<ApplicationError, UUID> {
        val id = patientRepository.addPatient(patient)
        return Either.Right(id)
    }
}
