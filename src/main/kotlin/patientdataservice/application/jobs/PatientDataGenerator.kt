package patientdataservice.application.jobs

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import patientdataservice.application.models.PatientDataRequest
import patientdataservice.application.models.PatientRequest
import patientdataservice.domain.services.PatientDataService
import patientdataservice.domain.services.PatientService
import kotlin.random.Random

@Component
class PatientDataGenerator(
    private val patientService: PatientService,
    private val patientDataService: PatientDataService
) {
    @Scheduled(fixedRate = 5 * 1000) // Simulate data reception every 5 seconds
    fun receiveData() {
        val patients = patientService.getAllPatients()

        if (patients.size < 5) {
            val name = "John Doe ${patients.size + 1}"
            patientService.addPatient(
                PatientRequest(name)
            )

            for (patient in patients) {
                val newPatientData = PatientDataRequest(
                    bloodPressure = "${Random.nextInt(100, 120)}/${Random.nextInt(80, 100)}",
                    heartRate = Random.nextInt(60, 150)
                )
                patientDataService.addPatientData(newPatientData, patient.patientId)
            }
        }
    }
}
