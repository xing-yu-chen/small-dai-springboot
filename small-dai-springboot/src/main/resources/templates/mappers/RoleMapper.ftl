<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.smalldai.test.mapper.RoleMapper">
    <resultMap id="getRoleTOByIdMap" type="${springPackageName}.to.RoleTO">
        <id column="r_id" property="rId"></id>
        <result column="r_name" property="rName"></result>
        <result column="r_remark" property="rRemark"></result>
    </resultMap>
    <select id="getRoleTOById" resultMap="getRoleTOByIdMap">
        SELECT r_id, r_name, r_remark
        FROM t_role
        WHERE r_id = <#noparse>#{</#noparse>rId} and is_deleted = 0
    </select>
</mapper>