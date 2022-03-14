package garden.druid.chia.types.farmer;

import java.util.ArrayList;

public class Harvester {

	private Connection connection;
	private ArrayList<String> failed_to_open_filenames, no_key_filenames;
	private ArrayList<Plot> plots;
	
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public ArrayList<String> getFailed_to_open_filenames() {
		return failed_to_open_filenames;
	}
	public void setFailed_to_open_filenames(ArrayList<String> failed_to_open_filenames) {
		this.failed_to_open_filenames = failed_to_open_filenames;
	}
	public ArrayList<String> getNo_key_filenames() {
		return no_key_filenames;
	}
	public void setNo_key_filenames(ArrayList<String> no_key_filenames) {
		this.no_key_filenames = no_key_filenames;
	}
	public ArrayList<Plot> getPlots() {
		return plots;
	}
	public void setPlots(ArrayList<Plot> plots) {
		this.plots = plots;
	}
}
