<html>
<head>
<#include "common.ftl">
    <script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/cus.linkMan.js"></script>
</head>
<body>
<table id="dg" title="客户信息管理" class="easyui-datagrid" pagination="true"
       rownumbers="true" url="${ctx}/linkMan/query?cusId=${customer.id}"
       fit="true" toolbar="#tb">
    <thead data-options="frozen:true">
    <tr>
        <th field="id" hidden="hidden"></th>
        <th field="cus_id" hidden="hidden"></th>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="linkName" width="100" align="center">联系人</th>
        <th field="sex" width="50" align="center" formatter="genderformat">性别</th>
        <th field="zhiwei" width="100" align="center">职位</th>
        <th field="officePhone" width="150" align="center">办公电话</th>
        <th field="phone" width="150" align="center">手机</th>
        <th field='createDate'>创建时间</th>
        <th field='updateDate'>更新时间</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="javascript:openLinkManAddDialog()"
           class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:openLinkManModifyDialog()"
            class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:delLinkMan()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">删除</a>
        </a>
    </div>
    <div>
        &nbsp;联系人：&nbsp;<input type="text" id="link_name" size="20"
                               onkeydown="if(event.keyCode==13) searchCustomer()"/>
        &nbsp;办公电话：&nbsp;<input type="number" id="office_phone" size="20"
                                onkeydown="if(event.keyCode==13) searchCustomer()"/>
        &nbsp;手机：&nbsp;<input type="number" id="phone" size="20"
                              onkeydown="if(event.keyCode==13) searchCustomer()"/>
        <a href="javascript:searchCustomer()" class="easyui-linkbutton"
           iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>
<script>
    var cusId = ${customer.id}
</script>

<div id="dlg" class="easyui-dialog"
     style="width:750px;height:450px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons" title="添加客户信息">
    <form id="fm" method="post">
        <input type="hidden" id="id" name="id"/>
        <input type="hidden" id="cusId" name="cusId" value="${customer.id}"/>
        <table cellspacing="8px" style="margin-top: 10px">
            <tr>
                <td style="width: 50px">
                    联系人:
                </td>
                <td style="width: 150px">
                    <input type="text" style="width: 145px" name="linkName" id="linkName" class="easyui-validatebox"/>
                </td>
            </tr>
            <tr>
                <td>
                    职位：
                </td>
                <td>
                    <input type="text" style="width: 145px" name="zhiwei" id="zhiwei" class="easyui-validatebox"/>
                </td>
            </tr>
            <tr>
                <td>
                    性别：
                </td>
                <td>
                    <input type="radio" id="sex" name="sex" value="1"/>男 &nbsp;
                    <input type="radio" name="sex" value="0"/>女
                </td>
            </tr>
            <tr>
                <td>
                    办公电话：
                </td>
                <td>
                    <input type="number" name="officePhone" id="officePhone" style="width: 145px"/>
                </td>
            </tr>
            <tr>
                <td>
                    手机：
                </td>
                <td>
                    <input type="number" name="phone" id="phone" style="width: 145px" class="easyui-validatebox"/>
                </td>
            </tr>
        </table>
    </form>
    <div id="dlg-buttons">
        <a href="javascript:saveOrUpdateLinkMan()" class="easyui-linkbutton"
           iconCls="icon-ok">保存</a>
        <a href="javascript:closeLinkManDialog()"
           class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
    </div>
</div>


</body>
</html>