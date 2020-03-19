package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGMemberLevelPermEleService;
import org.smartwork.dal.entity.ZGMemberLevelPermEle;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"会员等级权限要素权限管理"})
@RestController
@RequestMapping("/zgmemberlevelpermele")
public class ZGMemberLevelPermEleController extends BaseProvider<IZGMemberLevelPermEleService, ZGMemberLevelPermEle> {
}