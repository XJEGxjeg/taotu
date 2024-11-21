package com.taotu.hv.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotu.hv.model.entity.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface JobMapper extends BaseMapper<Job> {
    @Select("SELECT j.*, t.name as type_name FROM t_job j " +
            "LEFT JOIN t_job_type t ON j.type_id = t.id " +
            "WHERE j.id = #{id} AND j.is_deleted = 0")
    Job selectJobWithType(Long id);
}