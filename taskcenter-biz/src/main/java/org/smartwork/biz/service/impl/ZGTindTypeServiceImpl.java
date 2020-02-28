package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGTindTypeService;
import org.smartwork.dal.entity.ZGTindType;
import org.smartwork.dal.mapper.ZGTindTypeMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGTindTypeServiceImpl extends ServiceImpl<ZGTindTypeMapper, ZGTindType> implements IZGTindTypeService {
}