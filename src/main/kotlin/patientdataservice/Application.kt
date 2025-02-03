package patientdataservice

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
class Application

fun main(args: Array<String>) {
    val logger = KotlinLogging.logger {}
    logger.info { "PATIENT DATA SERVICE IS RUNNING ..." }
    runApplication<Application>(*args)
}
