webpackJsonp([42],{pxj0:function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});r("Nv5k");var a=r("bjOP"),l=r("7GrU"),i={inject:["reload"],name:"jiaoyuguihua",data:function(){return{is_editor:!0,form:{name:"",finishtime:"",people:[],partment:"",level:"",danwei:"",grade:"",role:localStorage.getItem("ms_role"),shenbao:localStorage.getItem("ms_badge"),path:""},teacher_list:[],rules:{name:[{required:!0,message:"必填",trigger:"blur"}],level:[{required:!0,message:"必填",trigger:"blur"}],danwei:[{required:!0,message:"必填",trigger:"blur"}],grade:[{required:!0,message:"必填",trigger:"blur"}],partment:[{required:!0,message:"必填",trigger:"blur"}],finishtime:[{required:!0,message:"必填",trigger:"blur"}],people:[{required:!0,message:"必填",trigger:"blur"}],path:[{required:!0,message:"必填",trigger:"blur"}]}}},created:function(){var e=this;Object(l.m)().then(function(t){e.teacher_list=t})},methods:{uploadSuccess:function(e,t,r){this.form.path=e},handleChange:function(e,t){this.$message.warning("当前限制选择 1 个文件，请删除后继续上传！")},onSubmit:function(){var e=this;this.$refs.subform.validate(function(t){t?Object(a.h)(e.form).then(function(t){0!==t.data?e.$message.success("添加成功"):e.$message.error("添加失败，教研研成果已被申报"),e.reload()}):e.$message.error("请完善信息")})}}},s={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("div",{staticClass:"crumbs"},[r("el-breadcrumb",{attrs:{separator:"/"}},[r("el-breadcrumb-item",[r("i",{staticClass:"el-icon-tickets"}),e._v(" 教育规划项目申报")])],1)],1),e._v(" "),r("div",{staticClass:"container"},[r("div",{staticClass:"form-box"},[r("el-form",{ref:"subform",attrs:{model:e.form,"label-width":"100px",rules:e.rules}},[r("el-form-item",{attrs:{label:"项目名称",prop:"name"}},[r("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"结题单位",prop:"danwei"}},[r("el-input",{model:{value:e.form.danwei,callback:function(t){e.$set(e.form,"danwei",t)},expression:"form.danwei"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"项目级别",prop:"level"}},[r("el-input",{model:{value:e.form.level,callback:function(t){e.$set(e.form,"level",t)},expression:"form.level"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"结题等级",prop:"grade"}},[r("el-input",{model:{value:e.form.grade,callback:function(t){e.$set(e.form,"grade",t)},expression:"form.grade"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"部门",prop:"partment"}},[r("el-input",{model:{value:e.form.partment,callback:function(t){e.$set(e.form,"partment",t)},expression:"form.partment"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"参与人情况",prop:"people"}},[r("el-select",{attrs:{multiple:"",filterable:""},model:{value:e.form.people,callback:function(t){e.$set(e.form,"people",t)},expression:"form.people"}},e._l(e.teacher_list,function(e){return r("el-option",{key:e.badge,attrs:{label:e.badge+"—"+e.name,value:e.badge}})}))],1),e._v(" "),r("el-form-item",{attrs:{label:"结题时间",prop:"finishtime"}},[r("el-col",{attrs:{span:11}},[r("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"选择日期"},model:{value:e.form.finishtime,callback:function(t){e.$set(e.form,"finishtime",t)},expression:"form.finishtime"}})],1)],1),e._v(" "),r("el-form-item",{attrs:{label:"证明材料",prop:"path"}},[r("el-form",{ref:"form",attrs:{model:e.form,"label-width":"70px"}},[r("el-upload",{staticClass:"upload-demo",attrs:{drag:"",accept:".zip",action:"http://localhost:8080/JiaoYuGuiHuaMetials",limit:1,"on-exceed":e.handleChange,"on-success":e.uploadSuccess,"show-file-list":!0}},[r("i",{staticClass:"el-icon-upload"}),e._v(" "),r("div",{staticClass:"el-upload__text"},[e._v("将压缩包拖到此处，或"),r("em",[e._v("点击上传")])])])],1)],1),e._v(" "),r("el-form-item",[r("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("提交")]),e._v(" "),r("router-link",{attrs:{to:"/项目申报"}},[r("el-button",[e._v("取消")])],1)],1)],1)],1)])])},staticRenderFns:[]},o=r("VU/8")(i,s,!1,null,null,null);t.default=o.exports}});