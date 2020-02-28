package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGBigAttachService;
import org.smartwork.dal.entity.ZGBigAttach;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"任务竞标附件管理"})
@RestController
@RequestMapping("/zgbigattach")
public class ZGBigAttachController extends BaseProvider<IZGBigAttachService, ZGBigAttach> {
}