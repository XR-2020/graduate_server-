package com.example.graduate_sever.common.DTO;

/**
 * 用来接收前端查询的实例类
 * **/
public class SheKeDTO {
   private String key;
   private String type;
   private Integer pageIndex;
   private Integer pageSize ;

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getKey() {
      return key;
   }

   public void setKey(String key) {
      this.key = key;
   }

   public Integer getPageIndex() {
      return pageIndex;
   }

   public void setPageIndex(Integer pageIndex) {
      this.pageIndex = pageIndex;
   }

   public Integer getPageSize() {
      return pageSize;
   }

   public void setPageSize(Integer pageSize) {
      this.pageSize = pageSize;
   }
}