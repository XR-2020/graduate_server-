package com.example.graduate_sever.common;

import com.example.graduate_sever.model.ZheXian;

import java.util.List;

public class NewStatisticalRes {
    private List<TableData> xiaowaishijianjidi;
    private List<TableData> shijianlixiang;
    private List<TableData> shijianjiexiang;
    private List<TableData> jiaocaiyejidian;
    private List<TableData> jiaoyanyeji;
    private List<TableData> jiaoyanlunwen;
    private List<TableData> youxiubishe;
    private List<TableData> zongxiangjieti;
    private List<TableData> keyanhuojiang;

    public List<TableData> getXiaowaishijianjidi() {
        return xiaowaishijianjidi;
    }

    public void setXiaowaishijianjidi(List<TableData> xiaowaishijianjidi) {
        this.xiaowaishijianjidi = xiaowaishijianjidi;
    }

    public List<TableData> getShijianlixiang() {
        return shijianlixiang;
    }

    public void setShijianlixiang(List<TableData> shijianlixiang) {
        this.shijianlixiang = shijianlixiang;
    }

    public List<TableData> getShijianjiexiang() {
        return shijianjiexiang;
    }

    public void setShijianjiexiang(List<TableData> shijianjiexiang) {
        this.shijianjiexiang = shijianjiexiang;
    }

    public List<TableData> getJiaocaiyejidian() {
        return jiaocaiyejidian;
    }

    public void setJiaocaiyejidian(List<TableData> jiaocaiyejidian) {
        this.jiaocaiyejidian = jiaocaiyejidian;
    }

    public List<TableData> getJiaoyanyeji() {
        return jiaoyanyeji;
    }

    public void setJiaoyanyeji(List<TableData> jiaoyanyeji) {
        this.jiaoyanyeji = jiaoyanyeji;
    }

    public List<TableData> getJiaoyanlunwen() {
        return jiaoyanlunwen;
    }

    public void setJiaoyanlunwen(List<TableData> jiaoyanlunwen) {
        this.jiaoyanlunwen = jiaoyanlunwen;
    }

    public List<TableData> getYouxiubishe() {
        return youxiubishe;
    }

    public void setYouxiubishe(List<TableData> youxiubishe) {
        this.youxiubishe = youxiubishe;
    }

    public List<TableData> getZongxiangjieti() {
        return zongxiangjieti;
    }

    public void setZongxiangjieti(List<TableData> zongxiangjieti) {
        this.zongxiangjieti = zongxiangjieti;
    }

    public List<TableData> getKeyanhuojiang() {
        return keyanhuojiang;
    }

    public void setKeyanhuojiang(List<TableData> keyanhuojiang) {
        this.keyanhuojiang = keyanhuojiang;
    }

    public NewStatisticalRes(List<TableData> xiaowaishijianjidi, List<TableData> shijianlixiang, List<TableData> shijianjiexiang, List<TableData> jiaocaiyejidian, List<TableData> jiaoyanyeji, List<TableData> jiaoyanlunwen, List<TableData> youxiubishe, List<TableData> zongxiangjieti, List<TableData> keyanhuojiang) {
        this.xiaowaishijianjidi = xiaowaishijianjidi;
        this.shijianlixiang = shijianlixiang;
        this.shijianjiexiang = shijianjiexiang;
        this.jiaocaiyejidian = jiaocaiyejidian;
        this.jiaoyanyeji = jiaoyanyeji;
        this.jiaoyanlunwen = jiaoyanlunwen;
        this.youxiubishe = youxiubishe;
        this.zongxiangjieti = zongxiangjieti;
        this.keyanhuojiang = keyanhuojiang;
    }
}
