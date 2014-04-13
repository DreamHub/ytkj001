package com.ytkj.ytkj001.dao;

import java.util.ArrayList;
import java.util.List;

import com.ytkj.ytkj001.data.Material;

public class MaterialDao {
	private static List<Material> materials;
	static{
		materials=new ArrayList<Material>();
		
		materials.add(new Material("0020", "1873201", "02","1","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0020", "1873202", "02","2","锡丝 Sn0.5Ag0.7Cu φ1.2"));
		materials.add(new Material("0020", "1873203", "02","2","锡丝 Sn0.5Ag0.7Cu φ1.5"));
		materials.add(new Material("0020", "1873204", "03","2","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0020", "1873205", "03","1","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0020", "1873206", "03","2","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0030", "1873207", "04","2","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0030", "1873208", "04","2","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0030", "1873209", "04","1","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0030", "1873210", "04","2","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0030", "1873211", "05","1","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0030", "1873212", "05","2","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0030", "1873213", "06","2","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0030", "1873214", "06","1","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0030", "1873215", "06","2","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0040", "1873216", "07","2","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0040", "1873217", "07","2","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0040", "1873218", "07","1","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0040", "1873219", "07","2","锡丝 SnAg0.3Cu0.7 φ1.2"));
		materials.add(new Material("0040", "1873220", "07","2","锡丝 SnAg0.3Cu0.7 φ1.2"));
	} 
	public List<Material> findAll(){
		return materials;
	}
	public List<Material> findMaterialByXunhao(String xunhao){
		List<Material>  materialsTemp=new ArrayList<Material>();
		for (Material material : materials) {
			if(material.getXunhao().equals(xunhao)){
				materialsTemp.add(material);
			}
		}
		return materialsTemp;
	}
	public List<Material> findOriginalMaterials(String xunhao){
		List<Material>  materialsTemp=new ArrayList<Material>();
		for (Material material : materials) {
			if(material.getXunhao().equals(xunhao)&&material.getYouxianji().equals("1")){
				materialsTemp.add(material);
			}
		}
		return materialsTemp;
	}
	public List<Material> findMatchMaterials(Material originalMaterial){
		List<Material>  materialsTemp=new ArrayList<Material>();
		for (Material material : materials) {
			if(material.getXunhao().equals(originalMaterial.getXunhao())){
				if(material.getTidaixiangmuzu().equals(originalMaterial.getTidaixiangmuzu())){
					if(material.getYouxianji().equals("2")){
						materialsTemp.add(material);
					}
				}
			}
		}
		return materialsTemp;
	}

}
