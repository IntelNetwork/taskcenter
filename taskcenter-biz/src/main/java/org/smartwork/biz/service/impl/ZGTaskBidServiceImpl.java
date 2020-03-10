package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskBidService;
import org.smartwork.comm.model.ZGBigAttachDto;
import org.smartwork.comm.model.ZGTaskBidDto;
import org.smartwork.dal.entity.ZGBigAttach;
import org.smartwork.dal.entity.ZGTaskBid;
import org.smartwork.dal.mapper.ZGBigAttachMapper;
import org.smartwork.dal.mapper.ZGTaskBidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ZGTaskBidServiceImpl extends ServiceImpl<ZGTaskBidMapper, ZGTaskBid> implements IZGTaskBidService {

    @Autowired
    ZGBigAttachMapper zgBigAttachMapper;

    /***
     * Bidding方法概述:立即竞标关联附件
     * @param taskBidDto
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void Bidding(ZGTaskBidDto taskBidDto) {

        ZGTaskBid taskBid = new ZGTaskBid();
        BeanCopier.create(ZGTaskBidDto.class, ZGTaskBid.class, false)
                .copy(taskBidDto, taskBid, null);
        baseMapper.insert(taskBid);


        //任务竞标附件关联
        List<ZGBigAttachDto> zgBigAttachDtos = taskBidDto.getZgBigAttachDtos();
        if (ConvertUtils.isNotEmpty(zgBigAttachDtos)){
            Long taskBidId = taskBid.getId();
            ZGBigAttach attach = new ZGBigAttach();
            zgBigAttachDtos.stream().forEach(temp->{
                attach.setBidId(taskBidId);
                attach.setCnName(temp.getCnName());
                attach.setFilePath(temp.getFilePath());
                attach.setSuffix(temp.getSuffix());
                //执行添加操作
                zgBigAttachMapper.insert(attach);
            });
        }

    }


    /***
     * selectionTaskBid方法概述:需求方选标
     * @param taskBidDto
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void selectionTaskBid(ZGTaskBidDto taskBidDto) {
        ZGTaskBid taskBid = new ZGTaskBid();
        BeanCopier.create(ZGTaskBidDto.class, ZGTaskBid.class, false)
                .copy(taskBidDto, taskBid, null);
        baseMapper.updateById(taskBid);

    }


}