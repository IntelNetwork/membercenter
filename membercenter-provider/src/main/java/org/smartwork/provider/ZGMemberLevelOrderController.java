package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGMemberLevelOrderService;
import org.smartwork.dal.entity.ZGMemberLevelOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"会员订单管理"})
@RestController
@RequestMapping("/zgmemberlevelorder")
public class ZGMemberLevelOrderController extends BaseProvider<IZGMemberLevelOrderService, ZGMemberLevelOrder> {
}