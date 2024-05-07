package com.oasm.service;

import com.oasm.dao.InquirySignboardDAO;
import com.oasm.domain.InquiryEnterpriseVO;
import com.oasm.domain.InquirySignboardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InquirySignboardService {

    private final InquirySignboardDAO dao;

    public Integer insertSignboard(InquirySignboardVO vo) {return dao.insertSignboard(vo);}

    public List<InquirySignboardVO> selectSignboards(InquirySignboardVO vo) {
        return dao.selectSignboards(vo);
    }

    public List<InquirySignboardVO> selectAllSignAdkinds() {
        return dao.selectAllSignAdkinds();
    }

    public List<InquirySignboardVO> selectAllSignLightTypes() {
        return dao.selectAllSignLightTypes();
    }

    public List<InquiryEnterpriseVO> selectAllEnterprises() {return dao.selectAllEnterprises();}

    public String selectLastSignCode() {return dao.selectLastSignCode();}

    public Integer countAllSignboards(InquirySignboardVO vo) { return dao.countAllSignboards(vo); }
}
