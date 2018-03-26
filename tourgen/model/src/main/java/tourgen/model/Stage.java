package tourgen.model;
import java.util.ArrayList;

class Stage {

private ArrayList<Meet> stageMeets;
private StageType stageType;

    private Stage(StageType stageType) {
        this.stageType = stageType;
        stageMeets = new ArrayList<Meet>();
    }

    private void addMeettoStage(Meet newMeet) {
        stageMeets.add(newMeet);
    }

    private void removeMeetfromStage(Meet oldMeet) {
        stageMeets.remove(oldMeet);
    }

}