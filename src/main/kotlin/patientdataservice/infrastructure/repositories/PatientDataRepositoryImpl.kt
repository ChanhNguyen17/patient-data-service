package patientdataservice.infrastructure.repositories

import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import patientdataservice.application.models.PatientDataRequest
import patientdataservice.domain.models.PatientData
import patientdataservice.domain.repositories.PatientDataRepository
import java.time.OffsetDateTime
import java.util.UUID

@Repository
class PatientDataRepositoryImpl(
    private val jdbcClient: JdbcClient
) : PatientDataRepository {
    override fun getPatientDataById(patientId: UUID): List<PatientData> {
        return jdbcClient
            .sql(
                """
                SELECT patient_data_id, blood_pressure, heart_rate, timestamp
                FROM pds.patient_data
                WHERE patient_id = :patient_id::uuid
                """.trimIndent()
            )
            .param("patient_id", patientId)
            .query(PatientData::class.java)
            .list()
    }

    override fun addPatientData(patientDataRequest: PatientDataRequest, patientId: UUID): UUID {
        val patientDataId = UUID.randomUUID()
        jdbcClient
            .sql(
                """
                INSERT INTO pds.patient_data (patient_data_id, blood_pressure, heart_rate, timestamp, patient_id)
                VALUES (:patient_data_id::uuid, :blood_pressure, :heart_rate, :timestamp, :patient_id::uuid)
                """.trimIndent()
            )
            .param("patient_data_id", patientDataId)
            .param("blood_pressure", patientDataRequest.bloodPressure)
            .param("heart_rate", patientDataRequest.heartRate)
            .param("timestamp", OffsetDateTime.now())
            .param("patient_id", patientId)
            .update()
        return patientDataId
    }
}
