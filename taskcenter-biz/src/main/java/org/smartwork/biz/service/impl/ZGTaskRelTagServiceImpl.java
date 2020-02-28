package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGTaskRelTagService;
import org.smartwork.dal.entity.ZGTaskRelTag;
import org.smartwork.dal.mapper.ZGTaskRelTagMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGTaskRelTagServiceImpl extends ServiceImpl<ZGTaskRelTagMapper, ZGTaskRelTag> implements IZGTaskRelTagService {
}