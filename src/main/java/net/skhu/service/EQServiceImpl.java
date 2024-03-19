package net.skhu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.dto.EQ;
import net.skhu.mapper.EQMapper;

@Service
public class EQServiceImpl {
	@Autowired
	private EQMapper eqMapper;
	
	public List<EQ> selectEQList() throws Exception {
		return eqMapper.selectEQList();
	}
}
