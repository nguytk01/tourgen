package tourgen.view;

import javax.swing.JButton;


import org.jxmapviewer.viewer.GeoPosition;

import tourgen.model.Location;

import tourgen.model.School;

public class MapSiteItem {
	private GeoPosition gpsLocation;
	private School school;
	private Location loc;
	private SwingWaypoint swingWaypoint;
	private MapPanel mapPanel;
	public MapSiteItem(MapPanel panel, School schoolParam) {
	
		mapPanel = panel;
		school = schoolParam;
		loc = school.getSchoolLoc();
		mapPanel = panel;
		gpsLocation = new GeoPosition(loc.getLatitude(), loc.getLongitude());
		//swingWaypoint = new SwingWaypoint2(this, "",gpsLocation);
		
	}

	
	public SwingWaypoint getSwingWaypoint() {
		return swingWaypoint;
	}
	
	public JButton getButton() {
		return swingWaypoint.getButton();
	}
	
	public GeoPosition getGpsLocation() {
		return gpsLocation;
	}

	
	
	/**
	 * this method is called when a pin receives a MouseEntered event
	 */
	/*public void activate() {
		mapPanel.notifyGUIManagerOnSiteItemActivation(this.gSite);
	}
	
	*//**
	 * this method is called when a pin receives a MouseClick event
	 *//*
	public void click() {
		mapPanel.notifyGUIManagerOnSiteItemSelection(this.gSite);
	}*/
}
