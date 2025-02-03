package patientdataservice.domain.repositories

import patientdataservice.application.models.PatientRequest
import patientdataservice.domain.models.Patient
import java.util.UUID

interface PatientRepository {
    fun getPatientById(patientId: UUID): Patient?
    fun getAllPatients(): List<Patient>
    fun addPatient(patient: PatientRequest): UUID
}
