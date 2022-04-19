<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.smalldai.test.mapper.RoleMenuMapper">
    <resultMap id="listMenuTOByrIdMap" type="${springPackageName}.to.MenuTO">
        <id column="m_id" property="mId"></id>
        <result column="m_name" property="mName"></result>
        <result column="m_url" property="mUrl"></result>
        <result column="m_icon" property="mIcon"></result>
        <result column="m_parent_id" property="mParentId"></result>
        <result column="m_sort" property="mSort"></result>
    </resultMap>
    <select id="listMenuTOByrId" resultMap="listMenuTOByrIdMap">
        SELECT m.m_id, m.m_name, m.m_url, m.m_icon, m.m_parent_id, m.m_sort
        FROM t_menu as m, t_role_menu as rm
        WHERE m.m_id = rm.m_id
        WHERE rm.r_id = <#noparse>#{</#noparse>rId} and m.is_deleted = 0 and rm.is_deleted = 0
    </select>
</mapper>