package patientdataservice.domain

import java.util.UUID

open class ApplicationError(cause: String) : Throwable(cause)

class PatientNotFound(patientId: UUID) : ApplicationError("Patient $patientId not found")
