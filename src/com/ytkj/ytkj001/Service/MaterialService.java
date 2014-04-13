package com.ytkj.ytkj001.Service;

import java.util.ArrayList;
import java.util.List;

import com.ytkj.ytkj001.dao.MaterialDao;
import com.ytkj.ytkj001.data.Material;
import com.ytkj.ytkj001.data.MaterialRecord;

public class MaterialService {
	private MaterialDao materialDao;

	public MaterialDao getMaterialDao() {
		return materialDao;
	}

	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}
	
	public List<MaterialRecord> match(String xunhao){
		List<Material>  materialsTemp=materialDao.findOriginalMaterials(xunhao);
		List<MaterialRecord> records=new ArrayList<MaterialRecord>();
		for (Material material : materialsTemp) {
			MaterialRecord materialRecord=new MaterialRecord();
			materialRecord.setMaterial(material);
			materialRecord.setMatchMaterials(materialDao.findMatchMaterials(material));
			records.add(materialRecord);
		}
		return records;
	}

}
