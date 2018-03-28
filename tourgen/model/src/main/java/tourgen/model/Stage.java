package tourgen.model;
import java.util.ArrayList;

class Stage {

private ArrayList<Meet> stageMeets;
private StageType stageType;
private String stageHeader;
private String participantsHeader;
    Stage(StageType stageType, String stageHeaderArg, String participantsHeaderArg) {
        this.stageType = stageType;
        stageMeets = new ArrayList<Meet>();
        stageHeader = stageHeaderArg;
        participantsHeader = participantsHeaderArg;
    }

    void addMeetToStage(Meet newMeet) {
        stageMeets.add(newMeet);
    }

    void removeMeetFromStage(Meet oldMeet) {
        stageMeets.remove(oldMeet);
    }
    
    public String getStageTitle() {
    	switch (stageType) {
    		case SECTIONAL : return "Sectionals";
    		case REGIONAL: return "Regionals";
    		case SEMISTATE: return "Semi-states";
    		case STATEFINAL: return "State-final";
    		default: return "Stage";
    	}
    }
    
    public String getReport() {
    	StringBuilder builder = new StringBuilder();
    	builder.append(stageHeader);
    	for (int i = 1; i <= stageMeets.size(); i++) {
    		if (stageMeets.size() < 2) 
    			builder.append(stageMeets.get(i-1).getReport(0,participantsHeader));
    		else 
    			builder.append(stageMeets.get(i-1).getReport(i,participantsHeader));
    	}
    	return builder.toString();
    }
}