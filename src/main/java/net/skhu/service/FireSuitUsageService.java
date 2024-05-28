// FireSuitUsageService.java
package net.skhu.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import net.skhu.dto.FireSuitUsage;
import net.skhu.mapper.FireSuitUsageMapper;
import net.skhu.mapper.FireSuitUsageMapper.Order;
import net.skhu.model.FireSuitUsageEdit;
import net.skhu.model.Pagination1;

@Service
public class FireSuitUsageService {

	@Autowired
	public FireSuitUsageMapper fireSuitUsageMapper;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<FireSuitUsage> findAll(Pagination1 pagination) {
    	System.out.println("====="+pagination.toString());
    	int ii = fireSuitUsageMapper.getCount(pagination);
    	System.out.println("====="+ii);
    	pagination.setRecordCount(fireSuitUsageMapper.getCount(pagination));
        return fireSuitUsageMapper.findAll(pagination);
    }


    public Order[] getOrders() {
        return FireSuitUsageMapper.orders;
    }

    public FireSuitUsageEdit findOne(String id) {
        FireSuitUsage fireSuitUsage = fireSuitUsageMapper.findOne(id);
        return toEditModel(fireSuitUsage);
    }

    public FireSuitUsage toEditModel(FireSuitUsageEdit fireSuitUsage) {
        return modelMapper.map(fireSuitUsage, FireSuitUsage.class);
    }

    public FireSuitUsage findById(String id) {
        return fireSuitUsageMapper.findById(id);
    }

    public void insert(FireSuitUsageEdit eqEdit, BindingResult bindingResult,
            Pagination1 pagination) throws Exception {
        if (hasErrors(eqEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        FireSuitUsage eq = toDto(eqEdit);
        fireSuitUsageMapper.insert(eq);
        pagination.setEq("");
        int lastPage = (int)Math.ceil((double)fireSuitUsageMapper.getCount(pagination) / pagination.getSz());
        pagination.setPg(lastPage);

    }

    public void update(FireSuitUsageEdit eqEdit,
            BindingResult bindingResult) throws Exception {
        if (hasErrors(eqEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        FireSuitUsage eq = toDto(eqEdit);
        fireSuitUsageMapper.update(eq);
    }

    public void delete(String id) {
    	fireSuitUsageMapper.delete(id);
    }

    public FireSuitUsage toDto(FireSuitUsageEdit fireSuitUsage) {
        return modelMapper.map(fireSuitUsage, FireSuitUsage.class);
    }

    public FireSuitUsageEdit toEditModel(FireSuitUsage fireSuitUsage) {
        return modelMapper.map(fireSuitUsage, FireSuitUsageEdit.class);
    }

    public boolean hasErrors(FireSuitUsageEdit eqEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return true;
        FireSuitUsage eq = findById(eqEdit.getId());
        if (eq != null && eq.getId() != eqEdit.getId()) {
            bindingResult.rejectValue("id", null, "사용자가 중복됩니다.");
            return true;
        }
        return false;
    }
}
