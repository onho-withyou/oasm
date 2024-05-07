package com.oasm.service;

import com.oasm.dao.RegisterStatusDAO;
import com.oasm.domain.RegisterStatusVO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelFileService {

    @Autowired
    private RegisterStatusDAO registerStatusDAO;

    private Map<String, String> surveyResultMap = new HashMap<>();
    private Map<String, String> signKindMap = new HashMap<>();
    private Map<String, String> lightKindMap = new HashMap<>();

    public ExcelFileService() {
        surveyResultMap = new HashMap<>();
        signKindMap = new HashMap<>();
        lightKindMap = new HashMap<>();

        // 조사결과 매핑
        surveyResultMap.put("허가", "10");
        surveyResultMap.put("신고", "11");
        surveyResultMap.put("허가신고", "12");
        surveyResultMap.put("배제", "13");
        surveyResultMap.put("무허가요건구비", "14");
        surveyResultMap.put("무허가요건불구비", "15");
        surveyResultMap.put("무신고요건구비", "16");
        surveyResultMap.put("무신고요건불구비", "17");
        surveyResultMap.put("요건불비(수량초과)", "18");
        surveyResultMap.put("요건불비(위치장소)", "19");
        surveyResultMap.put("요건불비(규격", "20");
        surveyResultMap.put("요건불비(표시장소)", "21");

        // 광고물 종류 매핑
        signKindMap.put("가로형간판", "01");
        signKindMap.put("세로형간판", "02");
        signKindMap.put("돌출형간판", "03");
        signKindMap.put("공연간판", "04");
        signKindMap.put("옥상간판", "05");
        signKindMap.put("지주이용간판", "06");
        signKindMap.put("현수막", "07");
        signKindMap.put("애드벌룬", "08");
        signKindMap.put("벽보", "09");
        signKindMap.put("전단", "10");
        signKindMap.put("공공시설물 이용 광고물", "11");
        signKindMap.put("교통시설이용광고물", "12");
        signKindMap.put("입간판", "13");
        signKindMap.put("벽면이용현수막", "14");
        signKindMap.put("지주이용현수막", "15");
        signKindMap.put("현수막게시물", "16");
        signKindMap.put("가로등", "17");
        signKindMap.put("현수기", "18");
        signKindMap.put("기타", "19");


        // 조명 종류 매핑
        lightKindMap.put("전광", "01");
        lightKindMap.put("네온사인", "02");
        lightKindMap.put("일반전기", "03");
        lightKindMap.put("화공", "04");
        lightKindMap.put("비조명", "05");
        lightKindMap.put("기타", "06");

    }

        public List<RegisterStatusVO> readExcelFile(MultipartFile file) {
        List<RegisterStatusVO> registerStatusList = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);


            for (int rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // 3번째 행부터 시작 (인덱스 2)
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue; // 행이 비어있다면 건너뛰기

                RegisterStatusVO registerStatus = new RegisterStatusVO();

                // 엑셀의 각 열에서 데이터를 읽어와 객체의 속성으로 설정
                registerStatus.setEnterpriseNm(getCellValue(row, 1)); // 업체명 (B열)
                registerStatus.setSurveyDate(getCellValue(row, 2)); // 조사일자 (C열)

                /*registerStatus.setSignWidth(getCellValue(row, 3));*/
                // 가로크기 (D열)

                /*registerStatus.setSignLength(getCellValue(row, 4));*/
                // 세로크기 (E열)
                registerStatus.setLotNumberAdr(getCellValue(row, 5)); // 지번주소 (F열)
                registerStatus.setStreetAdr(getCellValue(row, 6)); // 도로명 주소 (G열)
                registerStatus.setStreetNo1(getCellValue(row, 7)); // 건물주번호 (H열)
                registerStatus.setStreetNo2(getCellValue(row, 8)); // 건물부번호 (I열)
                registerStatus.setSigunguAdr(getCellValue(row, 9)); // 시군구 (J열)
                registerStatus.setSurveyResult(getCellValue(row, 10)); // 조사결과 (K열)
                registerStatus.setSignKind(getCellValue(row, 11)); // 광고물 종류 (L열)
                registerStatus.setLightKind(getCellValue(row, 12)); // 조명 종류 (M열)
                /*registerStatus.setRoadYn(getCellValue(row, 13));*/
                // 도로 저촉 유무 (N열)*/

                // 엑셀의 각 열에서 데이터를 읽어와 객체의 속성으로 설정
                String signWidthStr = getCellValue(row, 3); // 가로크기 (D열)
                String signLengthStr = getCellValue(row, 4); // 세로크기 (E열)

                // signWidth 값이 숫자인지 확인하고, 아니라면 예외를 던짐
                if (signWidthStr.isEmpty() || !signWidthStr.matches("\\d+")) {
                    throw new IllegalArgumentException("가로크기가 유효하지 않습니다: " + signWidthStr);
                }
                BigDecimal signWidth = new BigDecimal(signWidthStr);
                registerStatus.setSignWidth(signWidth);

                // signLength 값도 동일하게 처리
                if (signLengthStr.isEmpty() || !signLengthStr.matches("\\d+")) {
                    throw new IllegalArgumentException("세로크기가 유효하지 않습니다: " + signLengthStr);
                }
                BigDecimal signLength = new BigDecimal(signLengthStr);
                registerStatus.setSignLength(signLength);


                // 여기에 roadYn 값을 설정하는 로직을 추가합니다.
                String roadYnValue = getCellValue(row, 13).trim(); // 공백 제거
                // "Y" 또는 "N" 값만 허용, 그 외의 경우 기본값 "N" 사용
                roadYnValue = ("Y".equals(roadYnValue) || "N".equals(roadYnValue)) ? roadYnValue : "N";
                registerStatus.setRoadYn(roadYnValue);




                registerStatusList.add(registerStatus);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return registerStatusList;
    }


    public List<RegisterStatusVO> previewExcelFile(MultipartFile file) {
        List<RegisterStatusVO> registerStatusList = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                RegisterStatusVO registerStatus = new RegisterStatusVO();

                // 엑셀의 각 열에서 데이터를 읽어와 객체의 속성으로 설정합니다.
                registerStatus.setEnterpriseNm(getCellValue(row, 1)); // 업체명 (B열)
                registerStatus.setSurveyDate(getCellValue(row, 2)); // 조사일자 (C열)
                // 가로크기 (D열) 및 세로크기 (E열)은 BigDecimal 타입으로 변환하여 설정합니다.
                String signWidthStr = getCellValue(row, 3);
                String signLengthStr = getCellValue(row, 4);

                if (!signWidthStr.isEmpty() && signWidthStr.matches("\\d+")) {
                    BigDecimal signWidth = new BigDecimal(signWidthStr);
                    registerStatus.setSignWidth(signWidth);
                }

                if (!signLengthStr.isEmpty() && signLengthStr.matches("\\d+")) {
                    BigDecimal signLength = new BigDecimal(signLengthStr);
                    registerStatus.setSignLength(signLength);
                }

                // 나머지 필드들도 마찬가지로 설정합니다.
                registerStatus.setLotNumberAdr(getCellValue(row, 5)); // 지번주소 (F열)
                registerStatus.setStreetAdr(getCellValue(row, 6)); // 도로명 주소 (G열)
                registerStatus.setStreetNo1(getCellValue(row, 7)); // 건물주번호 (H열)
                registerStatus.setStreetNo2(getCellValue(row, 8)); // 건물부번호 (I열)
                registerStatus.setSigunguAdr(getCellValue(row, 9)); // 시군구 (J열)
                registerStatus.setSurveyResult(getCellValue(row, 10)); // 조사결과 (K열)
                registerStatus.setSignKind(getCellValue(row, 11)); // 광고물 종류 (L열)
                registerStatus.setLightKind(getCellValue(row, 12)); // 조명 유무 (M열)
                // 도로 저촉 유무 (N열)은 "Y" 또는 "N" 값을 설정합니다.
                String roadYnValue = getCellValue(row, 13).trim();
                roadYnValue = ("Y".equals(roadYnValue) || "N".equals(roadYnValue)) ? roadYnValue : "N";
                registerStatus.setRoadYn(roadYnValue);

                // 객체를 리스트에 추가합니다.
                registerStatusList.add(registerStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registerStatusList;
    }



    private String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        DataFormatter formatter = new DataFormatter();
        // DataFormatter를 사용하여 셀의 값을 문자열로 변환
        // 이 방식은 날짜, 숫자, 문자열, 불리언, 수식을 포함한 모든 타입의 셀에 대해 올바르게 작동합니다.
        return formatter.formatCellValue(cell);
    }

    public void saveExcelDataToDatabase(MultipartFile file) {
        // 엑셀 파일에서 데이터를 읽어온다
        List<RegisterStatusVO> registerStatusList = readExcelFile(file);

        // 데이터 유효성 검증 및 매핑 적용
        for (RegisterStatusVO status : registerStatusList) {
            // 조사결과, 광고물 종류, 조명 유무의 매핑 적용
            String mappedSurveyResult = surveyResultMap.getOrDefault(status.getSurveyResult(), "기본값");
            String mappedSignKind = signKindMap.getOrDefault(status.getSignKind(), "기본값");
            String mappedLightKind = lightKindMap.getOrDefault(status.getLightKind(), "기본값");

            // 매핑된 값으로 업데이트
            status.setSurveyResult(mappedSurveyResult);
            status.setSignKind(mappedSignKind);
            status.setLightKind(mappedLightKind);

            // "Y" 또는 "N" 값만 유효한 값으로 간주
            if (!"Y".equals(status.getRoadYn()) && !"N".equals(status.getRoadYn())) {
                throw new IllegalArgumentException("유효성 검증 실패: roadYn 값이 유효하지 않습니다. - " + status.getRoadYn() + " for " + status.getEnterpriseNm());
            }
        }

        // 읽어온 데이터를 콘솔에 출력한다
        for (RegisterStatusVO status : registerStatusList) {
            System.out.println("Enterprise Name: " + status.getEnterpriseNm());
            System.out.println("Survey Date: " + status.getSurveyDate());
            System.out.println("Sign Width: " + status.getSignWidth());
            System.out.println("Sign Length: " + status.getSignLength());
            System.out.println("Lot Number Address: " + status.getLotNumberAdr());
            System.out.println("Street Address: " + status.getStreetAdr());
            System.out.println("Street No1: " + status.getStreetNo1());
            System.out.println("Street No2: " + status.getStreetNo2());
            System.out.println("Sigungu Address: " + status.getSigunguAdr());
            System.out.println("Survey Result: " + status.getSurveyResult());
            System.out.println("Sign Kind: " + status.getSignKind());
            System.out.println("Light Kind: " + status.getLightKind());
            System.out.println("Road Y/N: " + status.getRoadYn());
            System.out.println("----------------------------------------");
        }

        // 유효성 검증을 통과한 경우, DB에 저장
        if (!registerStatusList.isEmpty()) {
            for (RegisterStatusVO status : registerStatusList) {
                registerStatusDAO.excelInsertRegisterStatus(status); // 개별 객체를 DB에 저장
            }
        }
    }
}

/*
*/