package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGMemberLevelPermService;
import org.smartwork.dal.entity.ZGMemberLevelPerm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"会员权限管理"})
@RestController
@RequestMapping("/zgmemberlevelperm")
public class ZGMemberLevelPermController extends BaseProvider<IZGMemberLevelPermService, ZGMemberLevelPerm> {
}