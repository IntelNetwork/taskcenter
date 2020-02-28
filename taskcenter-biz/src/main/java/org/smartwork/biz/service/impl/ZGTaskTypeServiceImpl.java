package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGTaskTypeService;
import org.smartwork.dal.entity.ZGTaskType;
import org.smartwork.dal.mapper.ZGTaskTypeMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGTaskTypeServiceImpl extends ServiceImpl<ZGTaskTypeMapper, ZGTaskType> implements IZGTaskTypeService {
}