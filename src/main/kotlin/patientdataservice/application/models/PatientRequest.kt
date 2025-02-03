package patientdataservice.application.models

import com.fasterxml.jackson.annotation.JsonProperty

data class PatientRequest(
    @JsonProperty("name") val name: String
)

data class PatientDataRequest(
    @JsonProperty("blood_pressure") val bloodPressure: String,
    @JsonProperty("heart_rate") val heartRate: Int
)
