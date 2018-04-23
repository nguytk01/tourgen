package tourgen.controller;

public class MapAssistantController {
  private tourgen.util.IMapSidePane mapSidePane;
  
  public MapAssistantController(tourgen.util.IMapSidePane mapSidePaneArg) {
    mapSidePane = mapSidePaneArg;
  }

  public void showPinHostInfoSidePane(Object meet, Object school) {
    mapSidePane.displayPinHostInformation(meet, school);
  }
  
  public void showPinRegularInfoSidePane(Object meet, Object school) {
    mapSidePane.displayPinRegularInformation(meet, school);
  }
  
  public void hideSidePane(){
    mapSidePane.hide();
  }
}
