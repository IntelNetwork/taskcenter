package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTindTypeService;
import org.smartwork.dal.entity.ZGTindType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"行业类型管理"})
@RestController
@RequestMapping("/zgtindtype")
public class ZGTindTypeController extends BaseProvider<IZGTindTypeService, ZGTindType> {
}