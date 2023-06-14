package com.sgz.banlv.service.impl;

import com.sgz.banlv.entity.Picture;
import com.sgz.banlv.mapper.PictureMapper;
import com.sgz.banlv.service.IPictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgz.banlv.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 图片资源表 服务实现类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Slf4j
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements IPictureService {

//    通过picture_id查picture信息
    @Override
    public Result<Picture> GetPictureInfo(Long picture_id) {
        Picture picture = this.getById(picture_id);
        return Optional.ofNullable(picture)
                        .map(Result::success)
                        .orElse(Result.failure());

    }
}
