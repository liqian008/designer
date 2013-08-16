package com.bruce.designer.service.oauth;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.bean.AccessTokenInfoCriteria;
import com.bruce.designer.dao.AccessTokenInfoMapper;

@Service
public class AccessTokenServiceImpl implements IAccessTokenService {

	@Autowired
	private AccessTokenInfoMapper accessTokenInfoMapper;

	public int save(AccessTokenInfo t) {
		return accessTokenInfoMapper.insert(t);
	}

	public List<AccessTokenInfo> queryAll() {
		return accessTokenInfoMapper.selectByExample(null);
	}

	public int updateById(AccessTokenInfo t) {
		return accessTokenInfoMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return accessTokenInfoMapper.deleteByPrimaryKey(id);
	}

	public AccessTokenInfo loadById(Integer id) {
		return accessTokenInfoMapper.selectByPrimaryKey(id);
	}

    @Override
    public AccessTokenInfo loadAccessTokenInfo(String thirdpartyUid,
            String thirdpartyType) {
        AccessTokenInfoCriteria criteria = new AccessTokenInfoCriteria();
        criteria.createCriteria().andAccessTokenEqualTo(thirdpartyUid).andThirdpartyUidEqualTo(thirdpartyType);
        
        List<AccessTokenInfo> tokenList = accessTokenInfoMapper.selectByExample(criteria);
        if(tokenList!=null&&tokenList.size()==1){
            return tokenList.get(0);
        }
        return null;
    }

}
