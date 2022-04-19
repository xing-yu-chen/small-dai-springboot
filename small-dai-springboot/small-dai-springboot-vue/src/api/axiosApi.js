import { get, post, put, deleted } from '../axios'

/**
 *  发送注册Email
 * @param {*} params 
 * @returns 
 */
export function sengRegisterEmail(params){
    return get('/account/email/send', params)
}
/**
 * 注册
 */
export function register(params){
    return post('/account/register', params, true)
}
/**
 * 登录
 */
export function login(params){
    return post('/account/login', params, true)
}
/** 
 * 退出
*/
export function logout(){
    return get('/account/logout')
}

/**
 * 查询用户
 */
export function listUser(){
    return get('/sysUsers')
}

/** 
 * 修改用户信息
*/
export function updateUser(params){
    return put('/sysUsers', params);
}

/**
 * 创建项目
 */
export function createProject(params){
    return post('/genDatabases/create', params, true);
}

/**
 * 创建Banner
 */
export function createBanner(params){
    return post('/genProjects/banner', params, true)
}

/**
 * 创建yml
 */
export function createYml(params){
    return post('/genProjects/yml', params, true)
}

/**
 * 创建config
 */
export function createConfig(params){
    return post('/genProjects/config', params,true)
}

/**
 * 生成表
 */
export function createTableDB(params){
    return post('/genTables/create', params, true)
}

/**
 * 列出所有表
 */
export function listAllTables(params){
    return get('/genTables/listBydId', params)
}

/**
 * 列出某表下所有的字段
 */
export function listAllColumnsBytId(params){
    return get('/genTableColumn/columnsBytId', params)
}

/**
 * 建表加字段
 */
export function insertAllColumns(params){
    return post('/genTableColumn/create', params, true)
}

/**
 * 新增字段
 */
export function newColumn(param){
    return post('/genTableColumn/save', param, true)
}

/**
 * 插入mapper需求
 */
export function saveMapper(param){
    return post('/genProjects/mapper', param, true)
}

/**
 * 生成XML
 */
export function createXml(param){
    return post('/genProjects/xml', param, true)
}

/**
 * 生成实体/Pom
 */
export function creatEntityAndPom(params){
    return get('/genProjects/entity/' + params)
}

/**
 * 生成固定文件
 */
export function createFixed(params){
    return get('/genProjects/generateFixedFile/' + params)
}

/** 
 * 生成表
*/
export function generateTables(params){
    return get('/genTables/already/' + params)
}

/** 
 * 删除字段
*/
export function deletedColumn(param){
    return post('/genTableColumn/drop', param, true)
}
/**
 * 导出SQL脚本
 */
export function outputSqlScript(params){
    return get('/genSqlScript/sqldump', params )
}
/** 
 * 修改表字段名称
*/
export function updateColumnName(param){
    return post('/genTableColumn/name/update', param, true)
}
/** 
 * 修改字段类型
*/
export function updateColumnType(param){
    return post('/genTableColumn/type/update', param, true)
}
/** 
 * 修改字段备注
*/
export function updateColumnRemark(param){
    return post('/genTableColumn/commit/update', param, true)
}
/**
 * 修改表备注
 */
export function updateTableRemark(param){
    return post('/genTables/remark/update', param, true)
}
/**
 * 修改表名
 */
export function updateTableName(param){
    return post('/genTables/name/update', param, true)
}
/**
 * 获取某张表的所有字段
 */
export function listColumnsBytable(params){
    return get('/sysMenus/table', params)
}
/**
 * 获取某一个路径的所有列表
 */
export function listAllData(path){
    return get(path)
}
/**
 * 修改数据
 */
export function updateData(path, params){
    return post(path + '/update', params, true)
}