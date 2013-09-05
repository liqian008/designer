package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.Designer;
import com.bruce.designer.bean.DesignerCriteria;
import com.bruce.designer.dao.DesignerMapper;
import com.bruce.designer.service.DesignerService;

@Service
public class DesignerServiceImpl implements DesignerService {

	@Autowired
	private DesignerMapper designerMapper;

	public int save(Designer t) {
		return designerMapper.insert(t);
	}

	public List<Designer> queryAll() {
		return designerMapper.selectByExample(null);
	}

	public int updateById(Designer t) {
		return designerMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return designerMapper.deleteByPrimaryKey(id);
	}

	public Designer loadById(Integer id) {
		return designerMapper.selectByPrimaryKey(id);
	}
	
	public Designer loadByUserId(Integer userId) {
	    DesignerCriteria criteria = new DesignerCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId);
        List<Designer> designerList = designerMapper.selectByExample(criteria);
        if(designerList!=null&&designerList.size()==1){
            return designerList.get(0);
        }
        return null;
	}
}
