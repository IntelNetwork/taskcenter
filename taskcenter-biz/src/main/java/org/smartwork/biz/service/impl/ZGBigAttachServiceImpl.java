package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGBigAttachService;
import org.smartwork.dal.entity.ZGBigAttach;
import org.smartwork.dal.mapper.ZGBigAttachMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGBigAttachServiceImpl extends ServiceImpl<ZGBigAttachMapper, ZGBigAttach> implements IZGBigAttachService {
}