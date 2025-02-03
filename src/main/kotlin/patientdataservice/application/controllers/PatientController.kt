package patientdataservice.application.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import patientdataservice.application.models.PatientDataRequest
import patientdataservice.application.models.PatientRequest
import patientdataservice.application.models.PatientView
import patientdataservice.domain.services.PatientDataService
import patientdataservice.domain.services.PatientService
import java.util.UUID

@RestController
@RequestMapping("/api/patients")
@Tag(name = "Patient", description = "Patient management APIs")
class PatientController(
    private val patientService: PatientService,
    private val patientDataService: PatientDataService
) {
    @GetMapping()
    @Operation(summary = "Get all patients")
    fun getAllPatients(): List<PatientView> {
        return patientService.getAllPatients()
    }

    @GetMapping("/{patientId}")
    @Operation(summary = "Get patient by ID")
    fun getPatientById(@PathVariable patientId: UUID): ResponseEntity<PatientView> {
        return patientService.getPatientById(patientId)
            .fold(
                { ResponseEntity<PatientView>(HttpStatus.NOT_FOUND) },
                { ResponseEntity<PatientView>(it, HttpStatus.OK) }
            )
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new patient")
    fun addPatient(@RequestBody request: PatientRequest) {
        patientService.addPatient(request)
            .fold(
                { ResponseEntity<Void>(HttpStatus.BAD_REQUEST) },
                { ResponseEntity<Void>(HttpStatus.CREATED) }
            )
    }

    @PostMapping("/{patientId}/data")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add patient data")
    fun getPatientData(
        @PathVariable patientId: UUID,
        @RequestBody request: PatientDataRequest
    ) {
        patientDataService.addPatientData(request, patientId)
            .fold(
                { ResponseEntity<Void>(HttpStatus.BAD_REQUEST) },
                { ResponseEntity<Void>(HttpStatus.CREATED) }
            )
    }
}
