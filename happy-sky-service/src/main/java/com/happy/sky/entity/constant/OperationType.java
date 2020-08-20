package com.happy.sky.entity.constant;

public abstract class OperationType {
    public static enum VirtualDirectory {

        SYSTEM_SETTINGS("系统管理"),

        MESSAGE_MANAGER("消息管理"),

        PROJECT_MANAGER("项目管理"),

        DATA_RECORD("数据采集"),

        DATA_STATISTICS("数据统计"),

        DEVICE_MANAGER("设备管理"),

        ALARM_MANAGER("告警管理"),
        ;

        VirtualDirectory(String name){
            this.name = name ;
        }
        private String name;

        public String getName() {
            return name;
        }

        public String getCode(){
            return this.name() ;
        }

    }

    public static enum Module {
        /** 后台首页 **/
        HOME_PAGE ("后台首页",null),

        /** 系统管理 -子菜单 **/
        ADMIN_MANAGER("用户管理", VirtualDirectory.SYSTEM_SETTINGS),
        USER_AUDITOR("用户审核", VirtualDirectory.SYSTEM_SETTINGS),
        ROLE_MANAGER("角色权限", VirtualDirectory.SYSTEM_SETTINGS),
        OPERATIONAL_LOG("操作日志", VirtualDirectory.SYSTEM_SETTINGS),
        LOGIN_LOG("登录日志", VirtualDirectory.SYSTEM_SETTINGS),
        SYSTEM_SETUP("系统设置", VirtualDirectory.SYSTEM_SETTINGS),

        /** 消息管理 -子菜单**/
        MESSAGE_TEMPLATE("消息模板", VirtualDirectory.MESSAGE_MANAGER),
        MESSAGE_RECORD("消息记录", VirtualDirectory.MESSAGE_MANAGER),

        /** 项目管理-子菜单 **/
        PROJECT_SETTING("项目设备", VirtualDirectory.PROJECT_MANAGER),
        PROJECT_UNITS("单位列表", VirtualDirectory.PROJECT_MANAGER),

        /** 数据采集记录-子菜单 **/
        APP_DOWNLOAD_RECORD("APP下载", VirtualDirectory.DATA_RECORD),
        APP_REGISTER_RECORD("用户注册", VirtualDirectory.DATA_RECORD),
        APP_LOGIN_RECORD("用户登录", VirtualDirectory.DATA_RECORD),
        APP_VISIT_RECORD("访问记录", VirtualDirectory.DATA_RECORD),

        /** 数据统计 -子菜单**/
        APP_DOWNLOAD_STATISTICS("APP下载", VirtualDirectory.DATA_STATISTICS),
        APP_REGISTER_STATISTICS("用户注册", VirtualDirectory.DATA_STATISTICS),
        APP_LOGIN_STATISTICS("用户登录", VirtualDirectory.DATA_STATISTICS),
        APP_VISIT_STATISTICS("访问记录", VirtualDirectory.DATA_STATISTICS),


        /** 设备管理-子菜单 **/
        DEVICE_LIST("设备管理", VirtualDirectory.DEVICE_MANAGER),
        DEVICE_MESSAGE("设备消息", VirtualDirectory.DEVICE_MANAGER),
        DEVICE_CONFIG("设备配置", VirtualDirectory.DEVICE_MANAGER),

        DEVICE_TYPE("设备类型", VirtualDirectory.DEVICE_MANAGER),
        DEVICE_FIRM("厂商管理", VirtualDirectory.DEVICE_MANAGER),
        DEVICE_FIRM_MODEL("产品管理", VirtualDirectory.DEVICE_MANAGER),
        DEVICE_FIRM_MODEL_CONFIG("产品配置管理", VirtualDirectory.DEVICE_MANAGER),
        DEVICE_LOT("渠道管理", VirtualDirectory.DEVICE_MANAGER),

        /** 告警管理-子菜单 **/
        ALARM_LIST("报警列表", VirtualDirectory.ALARM_MANAGER),
        TROUBLE_LIST("故障列表", VirtualDirectory.ALARM_MANAGER),

        ;

        Module(String name,VirtualDirectory virtualDirectory){
            this.name = name ;
            this.virtualDirectory = virtualDirectory ;
        }
        private String name;
        private VirtualDirectory virtualDirectory ;

        public String getName() {
            return name;
        }

        public String getCode(){
            return this.name() ;
        }

        public VirtualDirectory getVirtualDirectory() {
            return virtualDirectory;
        }
    }

    public static enum Function {

        /** 系统管理 - 用户管理 **/
        ADMIN_MANAGER_QUERY(Module.ADMIN_MANAGER,"查询",false,false,""),
        ADMIN_MANAGER_ADD(Module.ADMIN_MANAGER,"新增",true,true,"新增系统用户${username}"),
        ADMIN_MANAGER_UPDATE(Module.ADMIN_MANAGER,"修改",true,true,"修改系统用户${username}"),
        ADMIN_MANAGER_LOCK_OR_UNLOCK(Module.ADMIN_MANAGER,"解冻/冻结",true,true,"${action}系统用户${username}"),
        ADMIN_MANAGER__AUTHORIZE(Module.ADMIN_MANAGER,"授权",true,true,"${username}的用户授权{projectName}-{unitNameName}管理权限"),
        ADMIN_MANAGER_EXPORT(Module.ADMIN_MANAGER,"导出",false,false,""),

        /** 系统管理 - 用户审核 **/
        USER_AUDITOR_QUERY(Module.USER_AUDITOR,"查询",false,false,""),
        USER_AUDITOR_AUDITOR(Module.USER_AUDITOR,"审核",true,true,"审核用户${username}"),
        USER_AUDITOR_EXPORT(Module.ADMIN_MANAGER,"导出",false,false,""),

        /** 系统管理 - 角色权限 **/
        ROLE_MANAGER_QUERY(Module.ROLE_MANAGER,"查询",false,false,""),
        ROLE_MANAGER_ADD(Module.ROLE_MANAGER,"新增",true,true,"新增系统角色${roleName}"),
        ROLE_MANAGER_UPDATE(Module.ROLE_MANAGER,"修改",true,true,"修改系统角色${roleName}"),
        ROLE_MANAGER_DELETE(Module.ROLE_MANAGER,"删除",true,true,"删除系统角色${roleName}"),
        ROLE_MANAGER_AUTHORIZE(Module.ROLE_MANAGER,"授权",true,true,"修改系统角色${roleName}的权限"),
        ROLE_MANAGER_EXPORT(Module.ADMIN_MANAGER,"导出",false,false,""),

        /** 系统管理 - 系统设置 **/
        SYSTEM_SETUP_QUERY(Module.SYSTEM_SETUP,"查询",false,false,""),
        SYSTEM_SETUP_UPDATE(Module.SYSTEM_SETUP,"修改",true,true,"修改系统设置${systemSetupName}"),
        SYSTEM_SETUP_EXPORT(Module.ADMIN_MANAGER,"导出",false,false,""),

        /** 系统管理 - 操作日志 **/
        OPERATIONAL_LOG_QUERY(Module.OPERATIONAL_LOG,"查询",false,false,""),
        OPERATIONAL_LOG_EXPORT(Module.OPERATIONAL_LOG,"导出",false,false,""),

        /** 系统管理 - 登录日志 **/
        LOGIN_LOG_QUERY(Module.LOGIN_LOG,"查询",false,false,""),
        LOGIN_LOG_EXPORT(Module.LOGIN_LOG,"导出",false,false,""),

        /** 消息管理 - 消息模板 **/
        MESSAGE_TEMPLATE_QUERY(Module.MESSAGE_TEMPLATE,"查询",false,false,""),
        MESSAGE_TEMPLATE_ADD(Module.MESSAGE_TEMPLATE,"新增",true,true,"新增消息模版${templateName}"),
        MESSAGE_TEMPLATE_UPDATE(Module.MESSAGE_TEMPLATE,"修改",true,true,"修改消息模版${templateName}"),
        MESSAGE_TEMPLATE_DELETE(Module.MESSAGE_TEMPLATE,"删除",true,true,"删除消息模版${templateName}"),
        MESSAGE_TEMPLATE_OPEN_OR_CLOSE(Module.MESSAGE_TEMPLATE,"开关",true,true,"${switch}消息模版${templateName}"),
        MESSAGE_TEMPLATE_EXPORT(Module.ADMIN_MANAGER,"导出",false,false,""),

        /** 消息管理 - 消息记录 **/
        MESSAGE_RECORD_QUERY(Module.MESSAGE_RECORD,"查询",false,false,""),
        MESSAGE_RECORD_SEND(Module.MESSAGE_RECORD,"发布",true,true,"发布站内消息，消息编号${messageTitle}"),
        MESSAGE_RECORD_EXPORT(Module.ADMIN_MANAGER,"导出",false,false,""),

        /** 项目管理 - 项目设置 **/
        PROJECT_SETTING_QUERY(Module.PROJECT_SETTING,"查询",false,false,""),
        PROJECT_SETTING_ADD(Module.PROJECT_SETTING,"新增",true,true,"新增项目${projectName}"),
        PROJECT_SETTING_DELETE(Module.PROJECT_SETTING,"删除",true,true,"删除项目${projectName}"),
        PROJECT_SETTING_UPDATE(Module.PROJECT_SETTING,"修改",true,true,"修改项目${projectName}"),
        PROJECT_SETTING_UPDATE_UNIT(Module.PROJECT_SETTING,"同步单位",true,true,"修改项目${projectName}"),
        PROJECT_SETTING_EXPORT(Module.PROJECT_SETTING,"导出",false,false,""),

        /** 项目管理 - 单位列表 **/
        PROJECT_UNITS_QUERY(Module.PROJECT_UNITS,"查询",false,false,""),
        PROJECT_UNITS_EXPORT(Module.PROJECT_UNITS,"导出",false,false,""),

        /** 数据采集记录 - APP下载 **/
        APP_DOWNLOAD_RECORD_QUERY(Module.APP_DOWNLOAD_RECORD,"查询",false,false,""),
        APP_DOWNLOAD_RECORD_EXPORT(Module.APP_DOWNLOAD_RECORD,"导出",false,false,""),
        /** 数据采集记录 - 用户注册 **/
        APP_REGISTER_RECORD_QUERY(Module.APP_REGISTER_RECORD,"查询",false,false,""),
        APP_REGISTER_RECORD_EXPORT(Module.APP_REGISTER_RECORD,"导出",false,false,""),
        /** 数据采集记录 - 用户登录 **/
        APP_LOGIN_RECORD_QUERY(Module.APP_LOGIN_RECORD,"查询",false,false,""),
        APP_LOGIN_RECORD_EXPORT(Module.APP_LOGIN_RECORD,"导出",false,false,""),
        /** 数据采集记录 - 用户登录 **/
        APP_VISIT_RECORD_QUERY(Module.APP_VISIT_RECORD,"查询",false,false,""),
        APP_VISIT_RECORD_EXPORT(Module.APP_VISIT_RECORD,"导出",false,false,""),

        /** 数据采集记录 - APP下载 **/
        APP_DOWNLOAD_STATISTICS_QUERY(Module.APP_DOWNLOAD_STATISTICS,"查询",false,false,""),
        APP_DOWNLOAD_STATISTICS_EXPORT(Module.APP_DOWNLOAD_STATISTICS,"导出",false,false,""),
        /** 数据采集记录 - 用户注册 **/
        APP_REGISTER_STATISTICS_QUERY(Module.APP_REGISTER_STATISTICS,"查询",false,false,""),
        APP_REGISTER_STATISTICS_EXPORT(Module.APP_REGISTER_STATISTICS,"导出",false,false,""),
        /** 数据采集记录 - 用户登录 **/
        APP_LOGIN_STATISTICS_QUERY(Module.APP_LOGIN_STATISTICS,"查询",false,false,""),
        APP_LOGIN_STATISTICS_EXPORT(Module.APP_LOGIN_STATISTICS,"导出",false,false,""),
        /** 数据采集记录 - 用户访问 **/
        APP_VISIT_STATISTICS_QUERY(Module.APP_VISIT_STATISTICS,"查询",false,false,""),
        APP_VISIT_STATISTICS_EXPORT(Module.APP_VISIT_STATISTICS,"导出",false,false,""),

        /** 设备管理 - 设备列表 **/
        DEVICE_LIST_QUERY(Module.DEVICE_LIST,"查询",false,false,""),
        DEVICE_LIST_ADD(Module.DEVICE_LIST,"新增",true,true,"新增设备${deviceName}"),
        DEVICE_LIST_DELETE(Module.DEVICE_LIST,"删除",true,true,"删除设备${deviceName}"),
        DEVICE_LIST_UPDATE(Module.DEVICE_LIST,"修改",true,true,"修改设备${deviceName}"),
        DEVICE_LIST_CONFIG(Module.DEVICE_LIST,"配置",true,true,"修改设备${deviceName}"),
        DEVICE_LIST_EXPORT(Module.DEVICE_LIST,"导出",false,false,""),

        /** 设备管理 - 设备消息列表 **/
        DEVICE_MESSAGE_QUERY(Module.DEVICE_MESSAGE,"查询",false,false,""),
        DEVICE_MESSAGE_EXPORT(Module.DEVICE_MESSAGE,"导出",false,false,""),
        /** 设备管理 - 设备远程配置模块 **/
        DEVICE_CONFIG_QUERY(Module.DEVICE_CONFIG,"查询",false,false,""),
        DEVICE_CONFIG_UPDATE(Module.DEVICE_CONFIG,"修改",true,true,"修改设备ID为${deviceId}配置"),

        /** 设备管理 - 设备类型**/
        DEVICE_TYPE_QUERY(Module.DEVICE_TYPE,"查询",false,false,""),
        DEVICE_TYPE_UPDATA(Module.DEVICE_TYPE,"更新",false,false,""),
        DEVICE_TYPE_EXPORT(Module.DEVICE_TYPE,"导出",false,false,""),

        /** 设备管理 - 设备品牌**/
        DEVICE_FIRM_QUERY(Module.DEVICE_FIRM,"查询",false,false,""),
        DEVICE_FIRM_ADD(Module.DEVICE_FIRM,"新增",true,true,"新增设备品牌${firmName}"),
        DEVICE_FIRM_DELETE(Module.DEVICE_FIRM,"删除",true,true,"删除设备品牌${firmName}"),
        DEVICE_FIRM_UPDATE(Module.DEVICE_FIRM,"修改",true,true,"修改设备品牌${firmName}"),
        DEVICE_FIRM_EXPORT(Module.DEVICE_FIRM,"导出",false,false,""),

        /** 设备管理 - 设备产品型号**/
        DEVICE_FIRM_MODEL_QUERY(Module.DEVICE_FIRM_MODEL,"查询",false,false,""),
        DEVICE_FIRM_MODEL_ADD(Module.DEVICE_FIRM_MODEL,"新增",true,true,"新增设备型号${firmModelName}"),
        DEVICE_FIRM_MODEL_DELETE(Module.DEVICE_FIRM_MODEL,"删除",true,true,"删除设备型号${firmModelName}"),
        DEVICE_FIRM_MODEL_UPDATE(Module.DEVICE_FIRM_MODEL,"修改",true,true,"修改设备型号${firmModelName}"),
        DEVICE_FIRM_MODEL_EXPORT(Module.DEVICE_FIRM_MODEL,"导出",false,false,""),
        /** 设备管理 - 产品属性配置模块 **/
        DEVICE_FIRM_MODEL_CONFIG_QUERY(Module.DEVICE_FIRM_MODEL_CONFIG,"查询",false,false,""),
        DEVICE_FIRM_MODEL_CONFIG_ADD(Module.DEVICE_FIRM_MODEL_CONFIG,"新增",true,true,"新增产品属性${firmModelConfigName}"),
        DEVICE_FIRM_MODEL_CONFIG_DELETE(Module.DEVICE_FIRM_MODEL_CONFIG,"删除",true,true,"删除产品属性${firmModelConfigName}"),
        DEVICE_FIRM_MODEL_CONFIG_UPDATE(Module.DEVICE_FIRM_MODEL_CONFIG,"修改",true,true,"修改产品属性${firmModelConfigName}"),

        /** 设备管理 - 物联网服务商**/
        DEVICE_LOT_QUERY(Module.DEVICE_LOT,"查询",false,false,""),
        DEVICE_LOT_ADD(Module.DEVICE_LOT,"新增",true,true,"新增物联网服务商${lotFacilitatorName}"),
        DEVICE_LOT_DELETE(Module.DEVICE_LOT,"删除",true,true,"删除物联网服务商${lotFacilitatorName}"),
        DEVICE_LOT_UPDATE(Module.DEVICE_LOT,"修改",true,true,"修改物联网服务商${lotFacilitatorName}"),
        DEVICE_LOT_EXPORT(Module.DEVICE_LOT,"导出",false,false,""),

        /** 告警管理 - 报警列表**/
        ALARM_LIST_QUERY(Module.ALARM_LIST,"查询",false,false,""),
        ALARM_LIST_DELETE(Module.ALARM_LIST,"删除",false,false,"删除设备${deviceName}记录"),
        ALARM_LIST_EXPORT(Module.ALARM_LIST,"导出",false,false,""),

        /** 告警管理 - 故障列表**/
        TROUBLE_LIST_QUERY(Module.TROUBLE_LIST,"查询",false,false,""),
        TROUBLE_LIST_EXPORT(Module.TROUBLE_LIST,"导出",false,false,""),

        ;

        Function(Module module,String name,boolean addLog,boolean isEdit,String template){
            this.module = module ;
            this.name = name ;
            this.addLog = addLog ;
            this.isEdit = isEdit ;
            this.template = template ;
        }
        private final Module module ;
        private String name;
        private boolean addLog = false ;
        private boolean isEdit = false ;
        private String template;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode(){
            return this.name() ;
        }

        public boolean isAddLog() {
            return addLog;
        }

        public boolean isEdit() {
            return isEdit;
        }

        public void setEdit(boolean edit) {
            isEdit = edit;
        }

        public Module getModule() {
            return module;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }
    }
}
