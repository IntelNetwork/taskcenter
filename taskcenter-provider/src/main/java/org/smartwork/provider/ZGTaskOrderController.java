package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTaskOrderService;
import org.smartwork.dal.entity.ZGTaskOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"任务订单管理"})
@RestController
@RequestMapping("/zgtaskorder")
public class ZGTaskOrderController extends BaseProvider<IZGTaskOrderService, ZGTaskOrder> {
}