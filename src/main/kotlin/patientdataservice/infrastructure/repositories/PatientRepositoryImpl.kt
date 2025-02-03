package patientdataservice.infrastructure.repositories

import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import patientdataservice.application.models.PatientRequest
import patientdataservice.domain.models.Patient
import patientdataservice.domain.repositories.PatientRepository
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Repository
class PatientRepositoryImpl(
    private val jdbcClient: JdbcClient
) : PatientRepository {
    override fun getPatientById(patientId: UUID): Patient? {
        return jdbcClient
            .sql("SELECT patient_id, name FROM pds.patients WHERE patient_id = :patient_id::uuid")
            .param("patient_id", patientId)
            .query(Patient::class.java)
            .optional()
            .getOrNull()
    }

    override fun getAllPatients(): List<Patient> {
        return jdbcClient
            .sql("SELECT patient_id, name FROM pds.patients")
            .query(Patient::class.java)
            .list()
    }

    override fun addPatient(patient: PatientRequest): UUID {
        val patientId = UUID.randomUUID()
        jdbcClient
            .sql(
                """
                INSERT INTO pds.patients (patient_id, name)
                VALUES (:patient_id::uuid, :name)
                """.trimIndent()
            )
            .param("patient_id", patientId)
            .param("name", patient.name)
            .update()
        return patientId
    }
}
