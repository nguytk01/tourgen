package tourgen.util;

import java.util.List;

public interface IMapDriver {
  public void showMeetList(List list);

  public List getMeetList();

  public void mapReplace(Object oldSchool, Object newSchool);

}
