package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTeamService;
import org.smartwork.dal.entity.ZGTeam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"团队管理"})
@RestController
@RequestMapping("/zgteam")
public class ZGTeamController extends BaseProvider<IZGTeamService, ZGTeam> {
}