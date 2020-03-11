package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGBigAttachService;
import org.smartwork.comm.model.ZGBigAttachDto;
import org.smartwork.dal.entity.ZGBigAttach;
import org.smartwork.dal.mapper.ZGBigAttachMapper;
import org.smartwork.dal.mapper.ext.ZGBigAttachExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZGBigAttachServiceImpl extends ServiceImpl<ZGBigAttachMapper, ZGBigAttach> implements IZGBigAttachService {

    @Autowired
    ZGBigAttachExtMapper zgBigAttachExtMapper;

    /***
     * listBidAttach方法概述:查询一个竞标的附件集合
     * @param id
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/11
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public List<ZGBigAttachDto> listBidAttach(Long id) {

        return zgBigAttachExtMapper.listBidAttach(id);
    }
}