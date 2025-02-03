package patientdataservice.domain.repositories

import patientdataservice.application.models.PatientDataRequest
import patientdataservice.domain.models.PatientData
import java.util.UUID

interface PatientDataRepository {
    fun getPatientDataById(patientId: UUID): List<PatientData>
    fun addPatientData(patientDataRequest: PatientDataRequest, patientId: UUID): UUID
}
