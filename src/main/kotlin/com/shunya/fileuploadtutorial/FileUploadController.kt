package com.shunya.fileuploadtutorial

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader

@RestController
class FileUploadController {

    val logger = LoggerFactory.getLogger(FileUploadController::class.java)

    @PostMapping("/fileupload")
    fun handleFileUpload(@RequestParam("file") file: MultipartFile): ServiceResponse {
        logger.info("handling fileupload for {}", file.name)
        val content = file.inputStream.bufferedReader().use(BufferedReader::readText)
        logger.info("file content = {}", content)
        return ServiceResponse.ok(content)
    }
}

data class ServiceResponse(val isSuccess: Boolean,
                           val data: String? = null) {

    companion object {
        fun ok(data: String): ServiceResponse {
            return ServiceResponse(isSuccess = true, data = data)
        }

        fun fail(): ServiceResponse {
            return ServiceResponse(isSuccess = false)
        }
    }
}