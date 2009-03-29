package edu.pdi2.decoders;

public abstract class Decoder {
	protected double UL_lon, UL_lat, UL_1, UL_2, UR_lon, UR_lat, UR_1, UR_2,
			LL_lon, LL_lat, LL_1, LL_2, LR_lon, LR_lat, LR_1, LR_2;
	protected int PPL, LPI;
	protected String gains_biases;

	/** El identificador del satelite */
	protected String satelliteId;

	protected String path;

	public Decoder(String path) {
		super();
		this.path = path;
		decode();
	}

	public double getUL_lon() {
		return UL_lon;
	}

	public double getUL_lat() {
		return UL_lat;
	}

	public double getUL_1() {
		return UL_1;
	}

	public double getUL_2() {
		return UL_2;
	}

	public double getUR_lon() {
		return UR_lon;
	}

	public double getUR_lat() {
		return UR_lat;
	}

	public double getUR_1() {
		return UR_1;
	}

	public double getUR_2() {
		return UR_2;
	}

	public double getLL_lon() {
		return LL_lon;
	}

	public double getLL_lat() {
		return LL_lat;
	}

	public double getLL_1() {
		return LL_1;
	}

	public double getLL_2() {
		return LL_2;
	}

	public double getLR_lon() {
		return LR_lon;
	}

	public double getLR_lat() {
		return LR_lat;
	}

	public double getLR_1() {
		return LR_1;
	}

	public double getLR_2() {
		return LR_2;
	}

	/** Puntos por linea */
	public int getPPL() {
		return PPL;
	}

	/** Lineas por imagen */
	public int getLPI() {
		return LPI;
	}

	protected abstract void decode();

	public String getGainsBiases() {
		return gains_biases;
	}

	public String getSatelliteId() {
		return satelliteId;
	}
}
