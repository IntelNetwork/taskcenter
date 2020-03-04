package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTaskBidService;
import org.smartwork.dal.entity.ZGTaskBid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"任务竞标记录管理"})
@RestController
@RequestMapping("/zgtaskbid")
public class ZGTaskBidController extends BaseProvider<IZGTaskBidService, ZGTaskBid> {
}