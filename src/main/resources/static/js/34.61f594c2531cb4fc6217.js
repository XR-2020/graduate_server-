webpackJsonp([34],{hOet:function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=i("7GrU"),a={name:"jiaoyulunwen",props:["edit"],mounted:function(){this.form=this.edit},data:function(){return{form:{},is_editor:!0,teacher_list:[],isInput:!0}},created:function(){var e=this;Object(n.m)().then(function(t){e.teacher_list=t})},methods:{change:function(){this.isInput=!1},handleChange:function(e){this.isPeople=!1,this.$forceUpdate()},closethis:function(){this.$parent.$parent.closeDialog()},onSubmit:function(){console.log(this.form)}}},l={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",[i("div",{staticClass:"crumbs"},[i("el-breadcrumb",{attrs:{separator:"/"}},[i("el-breadcrumb-item",[i("i",{staticClass:"el-icon-tickets"}),e._v("教研论文申报")])],1)],1),e._v(" "),i("div",{staticClass:"container"},[i("div",{staticClass:"form-box"},[i("el-form",{ref:"form",attrs:{model:e.form,"label-width":"100px"}},[i("el-form-item",{attrs:{label:"论文名称"}},[i("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"部门"}},[i("el-input",{model:{value:e.form.partment,callback:function(t){e.$set(e.form,"partment",t)},expression:"form.partment"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"参与人情况"}},[i("el-select",{attrs:{multiple:"",filterable:""},on:{change:e.handleChange},model:{value:e.form.people,callback:function(t){e.$set(e.form,"people",t)},expression:"form.people"}},e._l(e.teacher_list,function(e){return i("el-option",{key:e.badge,attrs:{label:e.badge+"—"+e.name,value:e.badge}})}))],1),e._v(" "),i("el-form-item",{attrs:{label:"完成时间"}},[i("el-input",{directives:[{name:"show",rawName:"v-show",value:e.isInput,expression:"isInput"}],attrs:{disabled:""},model:{value:e.form.finishtime,callback:function(t){e.$set(e.form,"finishtime",t)},expression:"form.finishtime"}}),e._v(" "),i("el-col",{attrs:{span:11}},[i("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"选择日期"},on:{change:e.change},model:{value:e.form.finishtime,callback:function(t){e.$set(e.form,"finishtime",t)},expression:"form.finishtime"}})],1)],1),e._v(" "),i("el-form-item",[i("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("提交")]),e._v(" "),i("el-button",{on:{click:e.closethis}},[e._v("取消")])],1)],1)],1)])])},staticRenderFns:[]},o=i("VU/8")(a,l,!1,null,null,null);t.default=o.exports}});