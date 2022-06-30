package module3;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.*;

public class LifeExpectancyMap extends PApplet {
	
	private UnfoldingMap map;
	Map<String, float> lifeExpByCountry;
	
	List<Feature> countries;
	List<Marker> countriesMarker;
	
	public void setup() {
		size(1000, 800, OPENGL);
		map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
		
		lifeExpByCountry = loadLifeExpByCountry(".csv");
		
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countriesMarker = MapUtils.createSimpleMarkers(countries);
		
		map.addMarkers(countriesMarker);
		shadeCountries();
		
		map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
	}
	
	private Map<String, float> loadLifeExpByCountry(String filename) {
		
		Map<String, float> mp = new HashMap<String, float>();
		
		String[] rows = loadStrings(filename);
		
		for(String row : rows) {
			String[] columns = row.split(',');
			if( ) {
				float value = Float.parseFloat(column[5]);
				mp.put(column[4],value);
			}
		}
		return mp;
	}
	
	private void shadeCountries() {
		for(Marker marker : countriesMarker) {
			String countryId = marker.getId();
			
			if(lifeExpByCountry.containsKey(countryId)) {
				float lifeExp = lifeExpByCountry.get(countryId);
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color(255-colorLevel, 150, colorLevel));
				
			}
			else {
				marker.setColor(color(150,150,150));
			}
		}
	}
	
	public void draw() {
		map.draw();
	}
	
}
