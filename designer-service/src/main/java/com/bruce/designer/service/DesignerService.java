package com.bruce.designer.service;


import com.bruce.baseSkeleton.service.IBaseService;

import com.bruce.designer.bean.Designer;

public interface DesignerService extends IBaseService<Designer, Integer>{
    
    public Designer  loadByUserId(Integer userId);
}
