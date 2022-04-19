<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.smalldai.test.mapper.RolePermissionMapper">
    <resultMap id="listPermissionByrIdMap" type="${springPackageName}.to.PermissionTO">
        <id column="p_id" property="pId"></id>
        <result column="p_name" property="pName"></result>
        <result column="p_remark" property="pRemark"></result>
    </resultMap>
    <select id="listPermissionByrId" resultMap="listPermissionByrIdMap">
        select p.p_id, p.p_name, p.p_remark
        from t_permission as p, t_role_permission as rp
        where rp.r_id = <#noparse>#{</#noparse>rId} and rp.p_id = p.p_id
    </select>
</mapper>