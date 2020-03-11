package org.smartwork.dal.mapper.ext;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.smartwork.comm.model.ZGBigAttachDto;
import org.smartwork.dal.entity.ZGBigAttach;

import java.util.List;

public interface ZGBigAttachExtMapper extends BaseMapper<ZGBigAttach> {

    /***
     * listBidId方法概述:查询一个竞标的附件集合
     * @param id
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/11
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<ZGBigAttachDto> listBidAttach(@Param(value = "id") Long id);
}