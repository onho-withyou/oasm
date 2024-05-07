package com.oasm.dao;

import com.oasm.domain.RegisterStatusVO;

import java.util.List;

public interface RegisterStatusDAO {
    void insertRegisterStatus(RegisterStatusVO registerStatusVO);

    void excelInsertRegisterStatus(RegisterStatusVO registerStatusVO);
    void batchInsertRegisterStatuses(List<RegisterStatusVO> registerStatusList);
}


