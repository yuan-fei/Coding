package math;

import java.math.BigInteger;
import java.util.Random;

public class RSA {

	public static void main(String[] args) {
		RSA rsa = new RSA();
		rsa.prepareKeys();
		int m = new Random().nextInt(rsa.keyPair.publicKey.modulo);
		System.out.println(rsa.keyPair);
		System.out.println("m: " + m);
		int em = rsa.encrypt(m);
		System.out.println("encrypted: " + em);
		System.out.println("decrypted: " + rsa.decrypt(em));
	}

	RSAKeyPair keyPair;

	public void prepareKeys() {
		int p = getRandomPrime();
		int q = getRandomPrime();
		int n = p * q;
		int e = getRandomPrime();
		int phi = (p - 1) * (q - 1);
		int d = ModularLinearEquationSolver.getMultiplicativeInverse(e, phi);
		keyPair = new RSAKeyPair(e, d, n);
	}

	public int encrypt(int m) {
		return (int) FastPower.modularExp(m, keyPair.publicKey.key, keyPair.publicKey.modulo);
	}

	public int decrypt(int m) {
		return (int) FastPower.modularExp(m, keyPair.privateKey.key, keyPair.privateKey.modulo);
	}

	private static int getRandomPrime() {
		return BigInteger.probablePrime(15, new Random()).intValue();
	}
}

class RSAKey {
	int key;
	int modulo;

	public RSAKey(int key, int modulo) {
		this.key = key;
		this.modulo = modulo;
	}

	@Override
	public String toString() {
		return "<key: " + key + ", modulo: " + modulo + ">";
	}
}

class RSAKeyPair {
	RSAKey publicKey;
	RSAKey privateKey;

	public RSAKeyPair(int publicKey, int privateKey, int modulo) {
		this.publicKey = new RSAKey(publicKey, modulo);
		this.privateKey = new RSAKey(privateKey, modulo);
	}

	@Override
	public String toString() {
		return publicKey.toString() + privateKey.toString();
	}
}