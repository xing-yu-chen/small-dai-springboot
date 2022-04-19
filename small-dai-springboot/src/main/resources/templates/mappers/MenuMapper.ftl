<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${springPackageName}.mapper.MenuMapper">
    <resultMap id="listMenusMap" type="top.smalldai.test.to.MenuTO">
        <id column="m_id" property="mId"></id>
        <result column="m_name" property="mName"></result>
        <result column="m_url" property="mUrl"></result>
        <result column="m_icon" property="mIcon"></result>
        <result column="m_parent_id" property="mParentId"></result>
        <result column="m_sort" property="mSort"></result>
    </resultMap>
    <select id="listMenus"  resultMap="listMenusMap">
        select m_id, m_name, m_url, m_icon, m_parent_id, m_sort
        from t_menu where is_deleted = 0
    </select>
    <select id="listMenuTOById" resultMap="listMenusMap">
        select m_id, m_name, m_url, m_icon, m_parent_id, m_sort
        from t_menu where m_id = <#noparse>#{</#noparse>mId} and is_deleted = 0
    </select>
</mapper>