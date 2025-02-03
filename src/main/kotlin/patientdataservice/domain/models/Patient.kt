package patientdataservice.domain.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.UUID

data class Patient(
    @JsonProperty("patient_id") val patientId: UUID,
    @JsonProperty("name") val name: String
)

data class PatientData(
    @JsonProperty("data_id") val patientDataId: UUID,
    @JsonProperty("blood_pressure") val bloodPressure: String,
    @JsonProperty("heart_rate") val heartRate: Int,
    @JsonProperty("timestamp") val timestamp: OffsetDateTime
)
