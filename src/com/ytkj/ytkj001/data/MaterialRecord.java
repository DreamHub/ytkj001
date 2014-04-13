package com.ytkj.ytkj001.data;

import java.util.List;

public class MaterialRecord {
	private Material material;//原数据
	private List<Material> matchMaterials;
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public List<Material> getMatchMaterials() {
		return matchMaterials;
	}
	public void setMatchMaterials(List<Material> matchMaterials) {
		this.matchMaterials = matchMaterials;
	}
	
	

}
