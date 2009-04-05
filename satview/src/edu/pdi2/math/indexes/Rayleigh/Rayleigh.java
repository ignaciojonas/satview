package edu.pdi2.math.indexes.Rayleigh;


/**
 * Encapsula el ćalculo de todos los índices que se pueden extraer del efecto Rayleigh
 * @author fede
 *
 */
public abstract class Rayleigh {

	protected double[] taug = {0.01,0.03,0.02,0.02,0};
	protected double[] solct = {1957,1829,1557,1047,219.3};
	protected double[] taur = {0.154307432006356,0.0812153640523331,0.0451160542671836,0.01739208443938170,0.00108387573372285};;
	protected int diaJuliano = 357;
	protected double cenitSen = 35;
	protected double cenitSolar = 33.0;
	protected double azimuthSen = 0;
	protected double azimuthSolar = 80.0;

	public Rayleigh() {
		taug[0] = 0.01;taug[1] = 0.03;taug[2] = 0.02;taug[3] = 0.02;taug[4] = 0;
//		diaJuliano = 357;
//		cenitSen = 0;
//		cenitSolar = 33.0;
//		azimuthSen = 0;
//		azimuthSolar = 80.0;
	}
	public double[] getSolct() {
		return solct;
	}
	public void setSolct(double[] solct) {
		this.solct = solct;
	}
	public void setTaug(double[] taug) {
		this.taug = taug;
	}
	public void setTaur(double[] taur) {
		this.taur = taur;
	}
	public void setDiaJuliano(int diaJuliano) {
		this.diaJuliano = diaJuliano;
	}
	public void setCenitSen(double cenitSen) {
		this.cenitSen = cenitSen;
	}
	public void setCenitSolar(double cenitSolar) {
		this.cenitSolar = cenitSolar;
	}
	public void setAzimuthSen(double azimuthSen) {
		this.azimuthSen = azimuthSen;
	}
	public void setAzimuthSolar(double azimuthSolar) {
		this.azimuthSolar = azimuthSolar;
	}
	public double[] getSolc() {
		return solct;
	}

	public double[] getTaug() {
		return taug;
	}

	public double[] getTaur() {
		return taur;
	}

	public int getDiaJuliano() {
		return diaJuliano;
	}

	public double getCenitSen() {
		return cenitSen;
	}

	public double getCenitSolar() {
		return cenitSolar;
	}

	public double getAzimuthSen() {
		return azimuthSen;
	}

	public double getAzimuthSolar() {
		return azimuthSolar;
	}

	protected double getPhasePos() {
		double sc = getScatteringCosPos();
		return 0.75 * (1 + sc * sc);
	}
	
	protected double getPhaseNeg() {
		double sc = getScatteringCosNeg();
		return 0.75 * (1 + sc * sc);
	}

	//OK
	private double getScatteringCosNeg() {
		return -Math.cos(cenitSen * Math.PI / 180.0) * Math.cos(cenitSolar * Math.PI / 180.0) -
		Math.sin(cenitSen * Math.PI / 180.0) * Math.sin(cenitSolar * Math.PI / 180.0) *
		Math.cos(azimuthSen * Math.PI / 180.0 - azimuthSolar * Math.PI / 180.0);
	}
	//OK
	private double getScatteringCosPos() {
		
		return Math.cos(cenitSen * Math.PI / 180.0) * Math.cos(cenitSolar * Math.PI / 180.0) -
			Math.sin(cenitSen * Math.PI / 180.0) * Math.sin(cenitSolar * Math.PI / 180.0) *
			Math.cos(azimuthSen * Math.PI / 180.0 - azimuthSolar * Math.PI / 180.0);
	}

	protected double getDistanciaTierraSol() {
		return 1-0.0167*Math.cos(2*Math.PI*(getDiaJuliano()-3)/365);
	}

	public double []getRadiance(int []bandas) {
		double []ret = new double[bandas.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = getRadiance(bandas[i]);
		}
		return ret;
	}
	//OK
	public double getRadiance(int banda) {
		//int banda = ((Integer)band).intValue()-1;
		
		return solct[banda - 1] * 1 / Math.pow(getDistanciaTierraSol(),2) * taur[banda - 1] / 
				(4 * Math.PI * Math.cos(cenitSen * Math.PI / 180.0)) * 
				Math.exp(-taug[banda - 1] / Math.cos(cenitSen * Math.PI / 180.0)) *
				Math.exp(-taug[banda - 1] / Math.cos(cenitSolar * Math.PI / 180.0)) *
				(getPhaseNeg() + 0.052 * getPhasePos());
	}
	/**Devuelve la reflectividad que tiene una imagen en un punto. Depende tanto de los valores
	 * medidos por el satélite como de los datos de la ubicación del satélite, por eso es que
	 * se calcula dentro de esta clase.
	 * @param params es un Object[] con la siguiente estructura:<br>
	 * params[0] es un Integer que representa la <b>banda</b> de la que se quiere obtener la reflectancia
	 * params[1] es la <b>Radiancia</b> que la imagen tiene en un punto. Tipo <b>Double</b>
	 */
	/*
	public double getReflectance(int []bands, double lsat) {
		//Integer band = ((Integer)params[0]).intValue() - 1;
		//double lsat = (Double) params[1];
		double r = getDistanciaTierraSol();
		double []radiances = getRadiance(bands);
		
		return r * r * Math.PI * (lsat * getRadiance(band)) / 
		Math.cos(cenitSolar * Math.PI / 180.0) * solct[band];
	}
	*/
	/**
	 * 
	 */
	public double[] getReflectance(int[] bands, double[] lsats) {
		double r = getDistanciaTierraSol();
		double []radiances = getRadiance(bands);
		int cantBands = bands.length;
		
		double [] ret = new double[lsats.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = r * r * Math.PI * (lsats[i] - radiances[i%cantBands]) / 
			(Math.cos(cenitSolar * Math.PI / 180.0) * solct[bands[i%cantBands] - 1]);
		}
		
		
		return ret;
	}
	
	public StringBuffer printIndexes(){
		StringBuffer ret = new StringBuffer();
		
		ret.append("radiance [");
		for (int i=1;i<=5;++i)
			ret.append(getRadiance(i)+", ");
		ret.append("]\n");
//		ret.append("reflectance [");
//		for (int i=0;i<5;++i)
//			ret.append(getReflectance(i)+", ");
//		ret.append("]\n");
		ret.append("scattering positive cosin= "+getScatteringCosPos()+"\n");
		ret.append("scattering negative cosin= "+getScatteringCosNeg()+"\n");
		ret.append("Phase(+)= "+getPhasePos()+"\n");
		ret.append("Phase(-)= "+getPhaseNeg()+"\n");
		ret.append("sun distance= "+getDistanciaTierraSol());
		return ret;
	}
	
	
}
