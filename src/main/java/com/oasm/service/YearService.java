package com.oasm.service;

import com.oasm.dao.YearDAOImpl;
import com.oasm.domain.YearVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class YearService {

    private final YearDAOImpl dao;

    // 연도(5년치)
    public int[] years() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int[] years = new int[10];

        for(int i = 0; i < 10; i++) {
            years[i] += year;
            year--;
        }
        return years;
    }
    // 표 역정렬
    public int[] yearsReverse(YearVO yearVO){
        int year = Integer.parseInt(yearVO.getYear2());
        int count = Integer.parseInt(yearVO.getYear2()) - Integer.parseInt(yearVO.getYear());
        int[] arr = new int[count + 1];

        for (int i = 0; i <= count; i++) {
            arr[i] += year--;
        }

        if(yearVO.getOrder().equals("reverse")) {
            return arr;
        } else {
            int[] result = new int[arr.length];
            for (int i = arr.length - 1, j = 0; i >= 0; i--, j++) {
                result[j] = arr[i];
            }
            return result;
        }
    }
    // yearAdvType 검색버튼
    public int[] yearAdvTypeSearchButton(YearVO yearVO) {
        int year = Integer.parseInt(yearVO.getYear2());
        int num = Integer.parseInt(yearVO.getYear2()) - Integer.parseInt(yearVO.getYear());
        int[] countArr = new int[num + 1];
        int count = 0;

        for (int i = 0; i <= num; i++) {

            yearVO.setYear(String.valueOf(year - i));

            if(yearVO.getCheckbox()[0].equals("All")){
                yearVO.setCode_nm("All");
                if(yearVO.getSign_kind().equals("Adkind")) {
                    count += dao.typeOfSign(yearVO).size();
                } else if (yearVO.getSign_kind().equals("LightType")) {
                    count += dao.typeOfLight(yearVO).size();
                } else if (yearVO.getSign_kind().equals("Result")) {
                    count += dao.typeOfResult(yearVO).size();
                } else if (yearVO.getSign_kind().equals("Flag")) {
                    count += dao.typeOfRoad(yearVO).size();
                }
            } else {
                for (String j : yearVO.getCheckbox()) {
                    yearVO.setCode_nm(j);
                    if(yearVO.getSign_kind().equals("Adkind")) {
                        count += dao.typeOfSign(yearVO).size();
                    } else if (yearVO.getSign_kind().equals("LightType")) {
                        count += dao.typeOfLight(yearVO).size();
                    } else if (yearVO.getSign_kind().equals("Result")) {
                        count += dao.typeOfResult(yearVO).size();
                    } else if (yearVO.getSign_kind().equals("Flag")) {
                        count += dao.typeOfRoad(yearVO).size();
                    }
                }
            }

            countArr[i] += count;
            count = 0;
        }

        int[] result = new int[countArr.length];
        for (int i = countArr.length - 1, j = 0; i >= 0; i--, j++) {
            result[j] = countArr[i];
        }
        return result;
    }

}
