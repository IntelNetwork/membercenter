package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGMemberLevelService;
import org.smartwork.dal.entity.ZGMemberLevel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"会员等级管理"})
@RestController
@RequestMapping("/zgmemberlevel")
public class ZGMemberLevelController extends BaseProvider<IZGMemberLevelService, ZGMemberLevel> {
}