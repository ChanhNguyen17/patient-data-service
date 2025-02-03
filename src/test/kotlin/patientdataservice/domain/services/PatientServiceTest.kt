package patientdataservice.domain.services

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import patientdataservice.domain.PatientNotFound
import patientdataservice.domain.models.Patient
import patientdataservice.domain.models.PatientData
import patientdataservice.domain.repositories.PatientRepository
import java.time.OffsetDateTime
import java.util.UUID

class PatientServiceTest {

    private val patientRepository = mockk<PatientRepository>(relaxed = true)
    private val patientDataService = mockk<PatientDataService>(relaxed = true)
    private val patientService = PatientService(patientRepository, patientDataService)

    @Test
    fun `getAllPatients should returns list of PatientView`() {
        val patientId0 = UUID.randomUUID()
        val patientId1 = UUID.randomUUID()
        val patients = listOf(
            Patient(patientId0, "John Doe"),
            Patient(patientId1, "Jane Smith")
        )
        val patientData = listOf(
            PatientData(UUID.randomUUID(), "120/80", 80, OffsetDateTime.now())
        )

        every { patientRepository.getAllPatients() } returns patients
        every { patientDataService.getPatientDataById(eq(patientId0)) } returns patientData
        every { patientDataService.getPatientDataById(eq(patientId1)) } returns emptyList()

        val result = patientService.getAllPatients()

        assertEquals(2, result.size)
        assertSoftly(result[0]) {
            it.name shouldBe "John Doe"
            it.data shouldBe patientData
        }
        assertSoftly(result[1]) {
            it.name shouldBe "Jane Smith"
            it.data shouldBe emptyList()
        }
    }

    @Test
    fun `getPatientById should returns PatientView`() {
        val patientId = UUID.randomUUID()
        val patient = Patient(patientId, "John Doe")
        val patientData = listOf(
            PatientData(UUID.randomUUID(), "120/80", 80, OffsetDateTime.now())
        )

        every { patientRepository.getPatientById(patientId) } returns patient
        every { patientDataService.getPatientDataById(eq(patientId)) } returns patientData

        val result = patientService.getPatientById(patientId)

        assertTrue(result.isRight())
        result.map {
            assertEquals("John Doe", it.name)
            assertEquals(patientData, it.data)
        }
    }

    @Test
    fun `getPatientById should returns PatientNotFound`() {
        val patientId = UUID.randomUUID()

        every { patientRepository.getPatientById(patientId) } returns null

        val result = patientService.getPatientById(patientId)
        assertTrue(result.isLeft())
        result.mapLeft {
            assertTrue(it is PatientNotFound)
        }
    }
}
