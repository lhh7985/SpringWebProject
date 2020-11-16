package com.ho.hwang.service;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ho.hwang.dto.SrDTO;
import org.springframework.stereotype.Service;

import com.ho.hwang.mappers.UserMapper;
import com.ho.hwang.vo.SrVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SrService {
	private final UserMapper mapper;
	public final static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void insertSR(SrDTO.InsertSrDTO insertSrDTO, Principal principal) {
		Date time = new Date();

		String name = mapper.selectName(principal.getName());
		insertSrDTO.setSr_registrant(name);

		// 현재 날짜 삽입
		insertSrDTO.setSr_registrationDate(fmt.format(time));
		int type = mapper.selectCode(insertSrDTO.getType());
		System.out.println("타입 값 시벌 버냐고"+type);
		insertSrDTO.setSr_type(type);
		int cu_id = mapper.selectCustomerID(insertSrDTO.getCustomer_name());
		int p_id = mapper.selectProductID(insertSrDTO.getProduct_name());

		insertSrDTO.setCustomer_id(cu_id);
		insertSrDTO.setProduct_id(p_id);

		mapper.insertSR(insertSrDTO);
	}

	// SR 리스트 확인
	public List<SrDTO.SelectSrDTO> selectSR() {
		return mapper.selectSR();
	}

	// 각 고객사의 sr확인
	public List<SrDTO.SelectSrListDTO> selectSRList(int customer_id) {
		return mapper.selectSRList(customer_id);
	}

	// SR 내용확인
	public SrDTO.SelectSrDetailDTO selectSRDetail(int sr_id) {
		return mapper.selectSRDetail(sr_id);
	}

}
