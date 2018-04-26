package tourgen.model;

import java.io.Serializable;

public enum StageType implements Serializable {

  SECTIONAL, REGIONAL, SEMISTATE, STATEFINAL;
	private static StageType[] stageTypeArr = StageType.values();
	
	public StageType getLowerStageType() {
		for (int i = 0; i < stageTypeArr.length; i++) {
			if (this.equals(stageTypeArr[i])) {
				if ( i == 0) {
					return null;
				} else {
					return stageTypeArr[i-1];
				}
			}
			
		}
		return null;
	}
	
	public StageType getUpperStageType() {
		for (int i = 0; i < stageTypeArr.length; i++) {
			if (this.equals(stageTypeArr[i])) {
				if ( i == stageTypeArr.length - 1) {
					return null;
				} else {
					return stageTypeArr[i + 1];
				}
			}
			
		}
		return null;
	}
}