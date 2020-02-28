package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGTaskTagService;
import org.smartwork.dal.entity.ZGTaskTag;
import org.smartwork.dal.mapper.ZGTaskTagMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGTaskTagServiceImpl extends ServiceImpl<ZGTaskTagMapper, ZGTaskTag> implements IZGTaskTagService {
}