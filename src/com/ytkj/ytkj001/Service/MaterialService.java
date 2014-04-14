package com.ytkj.ytkj001.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.renderer.DefaultTreeRenderer;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableNode;
import org.jdesktop.swingx.ux.CheckTreeCellProvider;

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

	public List<MaterialRecord> match(String xunhao) {
		List<Material> materialsTemp = materialDao
				.findOriginalMaterials(xunhao);
		List<MaterialRecord> records = new ArrayList<MaterialRecord>();
		for (Material material : materialsTemp) {
			MaterialRecord materialRecord = new MaterialRecord();
			materialRecord.setMaterial(material);
			materialRecord.setMatchMaterials(materialDao
					.findMatchMaterials(material));
			records.add(materialRecord);
		}
		return records;
	}

	public String[][] productdata(JXTreeTable treeTable, Boolean[] bflag) {
		String[][] datas = new String[treeTable.getRowCount()][treeTable
				.getColumnCount() + 1];
		for (int i = 0; i < treeTable.getRowCount(); i++) {
			for (int j = 0; j <= treeTable.getColumnCount(); j++) {
				if (j < treeTable.getColumnCount()) {
					datas[i][j] = treeTable.getValueAt(i, j).toString();
				} else {
					if (bflag[i]) {
						datas[i][j] = "1";
					} else {
						datas[i][j] = "0";
					}
				}
			}
		}
		return datas;
	}
}
