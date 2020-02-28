package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGTaskAttachService;
import org.smartwork.dal.entity.ZGTaskAttach;
import org.smartwork.dal.mapper.ZGTaskAttachMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGTaskAttachServiceImpl extends ServiceImpl<ZGTaskAttachMapper, ZGTaskAttach> implements IZGTaskAttachService {
}