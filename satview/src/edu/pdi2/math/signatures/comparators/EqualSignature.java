package edu.pdi2.math.signatures.comparators;

public class EqualSignature implements SignatureComparator {
	private byte[] signature;
	
	public EqualSignature(byte[] signature) {
		super();
		this.signature = signature;
	}
	
	public boolean areEquivalent(byte[] signature) {
		for (int i = 0; i < signature.length; i++) {
			if(signature[i]!=this.signature[i]){
				return false;
			}
		}
		return true;
	}

}
