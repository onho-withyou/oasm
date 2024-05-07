package com.oasm.controller;


import com.oasm.domain.RegisterStatusVO;
import com.oasm.domain.SurveyStateVO;
import com.oasm.service.ExcelFileService;
import com.oasm.service.RegisterStatusService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);


    @Autowired
    private RegisterStatusService registerStatusService;

    @Autowired
    private ExcelFileService excelFileService;

    // 광고물 조사 내역
    @GetMapping("/stat/registerStatus")
    public String registerStatus() {
        return "stat/registerStatus";
    }





    @PostMapping("/api/registerStatus")
    public ResponseEntity<?> insertRegisterStatus(@RequestBody List<RegisterStatusVO> registerStatusList) {
        try {
            registerStatusService.addRegisterStatus(registerStatusList);
            return ResponseEntity.ok(Collections.singletonMap("message", "Register status added successfully"));
        } catch (DataIntegrityViolationException ex) {
            logger.error("Data integrity violation: ", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Invalid data format"));
        } catch (EntityNotFoundException ex) {
            logger.error("Entity not found: ", ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Requested entity not found"));
        } catch (IllegalArgumentException ex) {
            logger.error("Illegal argument: ", ex);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(Collections.singletonMap("error", "Illegal argument passed"));
        } catch (Exception ex) {
            logger.error("Error during registration: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "An unexpected error occurred: " + ex.getMessage()));
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException e) {
        logger.error("Handled ResponseStatusException: ", e);
        return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
    }



    @GetMapping("/registerStatusPopup")
    public String registerStatusPopup(Model model)  {
        model.addAttribute("message", "이 메시지가 팝업에 표시됩니다.");
        return "stat/registerStatusPopup";
    }

    @PostMapping("/api/uploadExcel")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            excelFileService.saveExcelDataToDatabase(file); // 서비스에서 DB 저장 로직을 호출
            return ResponseEntity.ok().body("파일이 성공적으로 업로드되었습니다.");
        } catch (IllegalArgumentException e) {
            logger.error("유효성 검증 실패: ", e);
            return ResponseEntity.badRequest().body("유효성 검증에 실패했습니다: " + e.getMessage());
        } catch (Exception e) {
            logger.error("파일 업로드 중 오류 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 중 오류가 발생했습니다.");
        }
    }

    // 새로운 파일 미리보기 API 메서드
    @PostMapping("/api/previewExcel")
    public ResponseEntity<?> previewExcelData(@RequestParam("file") MultipartFile file) {
        try {
            List<RegisterStatusVO> data = excelFileService.previewExcelFile(file);
            return ResponseEntity.ok(data); // 엑셀 파일 데이터를 JSON 형식으로 반환
        } catch (IllegalArgumentException e) {
            logger.error("유효성 검증 실패: ", e);
            return ResponseEntity.badRequest().body("유효성 검증에 실패했습니다: " + e.getMessage());
        } catch (Exception e) {
            logger.error("파일 읽기 중 오류 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 읽기 중 오류가 발생했습니다.");
        }
    }

}
