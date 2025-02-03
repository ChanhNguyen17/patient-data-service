package patientdataservice.application.models

import com.fasterxml.jackson.annotation.JsonProperty
import patientdataservice.domain.models.PatientData
import java.util.UUID

data class PatientView(
    @JsonProperty("patient_id") val patientId: UUID,
    @JsonProperty("name") val name: String,
    @JsonProperty("data") val data: List<PatientData>
)
