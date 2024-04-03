package net.skhu.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import net.skhu.dto.Eq;
import net.skhu.mapper.EqMapper;
import net.skhu.mapper.EqMapper.Order;
import net.skhu.model.EqEdit;
import net.skhu.model.Pagination1;

@Service
public class EQService {

	@Autowired
	public EqMapper eqMapper;
	private final ModelMapper modelMapper = new ModelMapper();

	public Order[] getOrders() {
        return EqMapper.orders;
    }

    public EqEdit findOne(int id) {
        Eq eqDto = eqMapper.findOne(id);
        return toEditModel(eqDto);
    }

    public Eq findByEQ_USER(String EQ_USER) {
        return eqMapper.findByEQ_USER(EQ_USER);
    }

    public List<Eq> findAll(Pagination1 pagination) {
        pagination.setRecordCount(eqMapper.getCount(pagination));
        return eqMapper.findAll(pagination);
    }

    public void insert(EqEdit eqEdit, BindingResult bindingResult,
            Pagination1 pagination) throws Exception {
        if (hasErrors(eqEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Eq eq = toDto(eqEdit);
        eqMapper.insert(eq);
        pagination.setEq("");
        int lastPage = (int)Math.ceil((double)eqMapper.getCount(pagination) / pagination.getSz());
        pagination.setPg(lastPage);

    }

    public void update(EqEdit eqEdit,
            BindingResult bindingResult) throws Exception {
        if (hasErrors(eqEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Eq eq = toDto(eqEdit);
        eqMapper.update(eq);
    }

    public void delete(int id) {
        eqMapper.delete(id);
    }

    public Eq toDto(EqEdit eqEdit) {
        return modelMapper.map(eqEdit, Eq.class);
    }

    public EqEdit toEditModel(Eq eqDto) {
        return modelMapper.map(eqDto, EqEdit.class);
    }

    public boolean hasErrors(EqEdit eqEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return true;
        Eq eq = findByEQ_USER(eqEdit.getEQ_USER());
        if (eq != null && eq.getEQ_USER() != eqEdit.getEQ_USER()) {
            bindingResult.rejectValue("EQ_USER", null, "사용자가 중복됩니다.");
            return true;
        }
        return false;
    }
}
