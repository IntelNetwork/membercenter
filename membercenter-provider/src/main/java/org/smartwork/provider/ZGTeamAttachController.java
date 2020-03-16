package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTeamAttachService;
import org.smartwork.dal.entity.ZGTeamAttach;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"团队附件管理"})
@RestController
@RequestMapping("/zgteamattach")
public class ZGTeamAttachController extends BaseProvider<IZGTeamAttachService, ZGTeamAttach> {
}