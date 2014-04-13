package com.ytkj.ytkj001.data;

public class Material {
	private String xunhao;//项目编号
	private String liaohao;//唯一号码
	private String tidaixiangmuzu;//替代项目组
	private String youxianji;//优先级 1表示原料2表示配料
	private String miaoshu;//描述
	
	public Material() {
		this.xunhao = "";
		this.liaohao = "";
		this.tidaixiangmuzu = "";
		this.youxianji="";
		this.miaoshu="";
	}

	public Material(String xunhao, String liaohao, String tidaixiangmuzu,String youxianji,String miaoshu) {
		this.xunhao = xunhao;
		this.liaohao = liaohao;
		this.tidaixiangmuzu = tidaixiangmuzu;
		this.youxianji=youxianji;
		this.miaoshu=miaoshu;
	}
	
	public String getMiaoshu() {
		return miaoshu;
	}

	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}

	public String getYouxianji() {
		return youxianji;
	}

	public void setYouxianji(String youxianji) {
		this.youxianji = youxianji;
	}

	public String getXunhao() {
		return xunhao;
	}
	public void setXunhao(String xunhao) {
		this.xunhao = xunhao;
	}
	public String getLiaohao() {
		return liaohao;
	}
	public void setLiaohao(String liaohao) {
		this.liaohao = liaohao;
	}
	public String getTidaixiangmuzu() {
		return tidaixiangmuzu;
	}
	public void setTidaixiangmuzu(String tidaixiangmuzu) {
		this.tidaixiangmuzu = tidaixiangmuzu;
	}

	@Override
	public String toString() {
		return liaohao;
	}
	
	

}
