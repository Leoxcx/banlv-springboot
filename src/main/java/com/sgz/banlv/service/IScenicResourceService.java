package com.sgz.banlv.service;

import com.sgz.banlv.vo.Result;
import com.sgz.banlv.entity.ScenicResource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sgz.banlv.entity.Scenicspot;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
public interface IScenicResourceService extends IService<ScenicResource> {

    Result<Scenicspot> ResourceToScenicSpotServlet(Long resource_id);

    Result<Boolean> IsAbleToPlay(Long resource_id, String user_openid);

    Result<Boolean> IsAbleToPlayById(Long resource_id, Long user_id);

    Result<Boolean> SavePlayRecord(Long resource_id);

    Result<Integer> GetResourcePlayRecord(Long resource_id);

    Result<ScenicResource> GetScenicResourceInfo(Long resource_id);
}
