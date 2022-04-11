package com.example.graduate_sever.common;

import com.example.graduate_sever.model.ZheXian;

import java.util.Arrays;
import java.util.List;

public class StatisticalRes {
    private List<ZheXian> zheXians;
    private List<TableData> chanxueyan;
    private List<TableData> hengxiangkeyan;
    private List<TableData> jiaoyanlunwen;
    private List<TableData> jiaoyanxiangmu;
    private List<TableData> jiaoyuguihua;
    private List<TableData> keyanlunwen;
    private List<TableData> keyanxiangmujiexiang;
    private List<TableData> pingguzhongxin;
    private List<TableData> rongyuchenghao;
    private List<TableData> ruanjianzhuzuo;
    private List<TableData> xuekejingsai;
    private List<TableData> zhuanli;
    private List<TableData> zhuzuo;
    private List<TableData> zongxiangkeyan;

    public StatisticalRes(List<ZheXian> zheXians, List<TableData> chanxueyan, List<TableData> hengxiangkeyan, List<TableData> jiaoyanlunwen, List<TableData> jiaoyanxiangmu, List<TableData> jiaoyuguihua, List<TableData> keyanlunwen, List<TableData> keyanxiangmujiexiang, List<TableData> pingguzhongxin, List<TableData> rongyuchenghao, List<TableData> ruanjianzhuzuo, List<TableData> xuekejingsai, List<TableData> zhuanli, List<TableData> zhuzuo, List<TableData> zongxiangkeyan) {
        this.zheXians = zheXians;
        this.chanxueyan = chanxueyan;
        this.hengxiangkeyan = hengxiangkeyan;
        this.jiaoyanlunwen = jiaoyanlunwen;
        this.jiaoyanxiangmu = jiaoyanxiangmu;
        this.jiaoyuguihua = jiaoyuguihua;
        this.keyanlunwen = keyanlunwen;
        this.keyanxiangmujiexiang = keyanxiangmujiexiang;
        this.pingguzhongxin = pingguzhongxin;
        this.rongyuchenghao = rongyuchenghao;
        this.ruanjianzhuzuo = ruanjianzhuzuo;
        this.xuekejingsai = xuekejingsai;
        this.zhuanli = zhuanli;
        this.zhuzuo = zhuzuo;
        this.zongxiangkeyan = zongxiangkeyan;
    }

    public List<TableData> getChanxueyan() {
        return chanxueyan;
    }

    public void setChanxueyan(List<TableData> chanxueyan) {
        this.chanxueyan = chanxueyan;
    }

    public List<TableData> getHengxiangkeyan() {
        return hengxiangkeyan;
    }

    public void setHengxiangkeyan(List<TableData> hengxiangkeyan) {
        this.hengxiangkeyan = hengxiangkeyan;
    }

    public List<TableData> getJiaoyanlunwen() {
        return jiaoyanlunwen;
    }

    public void setJiaoyanlunwen(List<TableData> jiaoyanlunwen) {
        this.jiaoyanlunwen = jiaoyanlunwen;
    }

    public List<TableData> getJiaoyanxiangmu() {
        return jiaoyanxiangmu;
    }

    public void setJiaoyanxiangmu(List<TableData> jiaoyanxiangmu) {
        this.jiaoyanxiangmu = jiaoyanxiangmu;
    }

    public List<TableData> getJiaoyuguihua() {
        return jiaoyuguihua;
    }

    public void setJiaoyuguihua(List<TableData> jiaoyuguihua) {
        this.jiaoyuguihua = jiaoyuguihua;
    }

    public List<TableData> getKeyanlunwen() {
        return keyanlunwen;
    }

    public void setKeyanlunwen(List<TableData> keyanlunwen) {
        this.keyanlunwen = keyanlunwen;
    }

    public List<TableData> getKeyanxiangmujiexiang() {
        return keyanxiangmujiexiang;
    }

    public void setKeyanxiangmujiexiang(List<TableData> keyanxiangmujiexiang) {
        this.keyanxiangmujiexiang = keyanxiangmujiexiang;
    }

    public List<TableData> getPingguzhongxin() {
        return pingguzhongxin;
    }

    public void setPingguzhongxin(List<TableData> pingguzhongxin) {
        this.pingguzhongxin = pingguzhongxin;
    }

    public List<TableData> getRongyuchenghao() {
        return rongyuchenghao;
    }

    public void setRongyuchenghao(List<TableData> rongyuchenghao) {
        this.rongyuchenghao = rongyuchenghao;
    }

    public List<TableData> getRuanjianzhuzuo() {
        return ruanjianzhuzuo;
    }

    public void setRuanjianzhuzuo(List<TableData> ruanjianzhuzuo) {
        this.ruanjianzhuzuo = ruanjianzhuzuo;
    }

    public List<TableData> getXuekejingsai() {
        return xuekejingsai;
    }

    public void setXuekejingsai(List<TableData> xuekejingsai) {
        this.xuekejingsai = xuekejingsai;
    }

    public List<TableData> getZhuanli() {
        return zhuanli;
    }

    public void setZhuanli(List<TableData> zhuanli) {
        this.zhuanli = zhuanli;
    }

    public List<TableData> getZhuzuo() {
        return zhuzuo;
    }

    public void setZhuzuo(List<TableData> zhuzuo) {
        this.zhuzuo = zhuzuo;
    }

    public List<TableData> getZongxiangkeyan() {
        return zongxiangkeyan;
    }

    public void setZongxiangkeyan(List<TableData> zongxiangkeyan) {
        this.zongxiangkeyan = zongxiangkeyan;
    }

    public List<ZheXian> getZheXians() {
        return zheXians;
    }

    public void setZheXians(List<ZheXian> zheXians) {
        this.zheXians = zheXians;
    }

    @Override
    public String toString() {
        return "StatisticalRes{" +
                "zheXians=" + zheXians +
                ", chanxueyan=" + chanxueyan +
                ", hengxiangkeyan=" + hengxiangkeyan +
                ", jiaoyanlunwen=" + jiaoyanlunwen +
                ", jiaoyanxiangmu=" + jiaoyanxiangmu +
                ", jiaoyuguihua=" + jiaoyuguihua +
                ", keyanlunwen=" + keyanlunwen +
                ", keyanxiangmujiexiang=" + keyanxiangmujiexiang +
                ", pingguzhongxin=" + pingguzhongxin +
                ", rongyuchenghao=" + rongyuchenghao +
                ", ruanjianzhuzuo=" + ruanjianzhuzuo +
                ", xuekejingsai=" + xuekejingsai +
                ", zhuanli=" + zhuanli +
                ", zhuzuo=" + zhuzuo +
                ", zongxiangkeyan=" + zongxiangkeyan +
                '}';
    }
}
