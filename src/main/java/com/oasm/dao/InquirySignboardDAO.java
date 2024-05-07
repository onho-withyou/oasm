package com.oasm.dao;

import com.oasm.domain.InquiryEnterpriseVO;
import com.oasm.domain.InquirySignboardVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InquirySignboardDAO {

    Integer insertSignboard(InquirySignboardVO vo);

    List<InquirySignboardVO> selectSignboards(InquirySignboardVO vo);

    List<InquirySignboardVO> selectAllSignAdkinds();

    List<InquirySignboardVO> selectAllSignLightTypes();

    List<InquiryEnterpriseVO> selectAllEnterprises();

    String selectLastSignCode();

    Integer countAllSignboards(InquirySignboardVO vo);
}
