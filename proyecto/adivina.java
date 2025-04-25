package contornos;

import java.util.*;

public class adivina_palabra {

	public static void main(String[] args) {

		char[] palabraAleatoria = generaPalabraAleatoria().toCharArray();

		int oportunidades = palabraAleatoria.length;

		Random random = new Random();
		HashSet<Integer> posicionesEnBlanco = new HashSet<>();
		while (posicionesEnBlanco.size() < palabraAleatoria.length / 2) {
			posicionesEnBlanco.add(random.nextInt(palabraAleatoria.length));
		}

		System.out.println("Bienvenido a adivina la palabra, tienes " + oportunidades + " oportunidades para ganar");
		System.out.println("La palabra a divinar es...\n");

		do {
			int[] arrayPosiciones = new int[palabraAleatoria.length];
			for (int i = 0; i < palabraAleatoria.length; i++) {
				if (posicionesEnBlanco.contains(i)) {
					System.out.print("_");
					arrayPosiciones[i] = 0;
				} else {
					System.out.print(palabraAleatoria[i]);
					arrayPosiciones[i] = 1;
				}
			}
			System.out.println();

			System.out.println("Digite una letra:");
			Scanner entrada = new Scanner(System.in);
			String letraString = entrada.nextLine();

			letraString = letraString.equals("") ? "@$%" : letraString.toLowerCase();
			char letra;
			if (letraString.length() > 1) {
				if (letraString.equalsIgnoreCase(String.valueOf(palabraAleatoria))) {
					System.out.println("!!Ganaste la palabra es: " + letraString);
					break;
				} else {
					oportunidades--;
					System.out.println("No acertaste ahora solo tienes " + oportunidades + " oportunidades");
				}
			} else {
				letra = letraString.charAt(0);
				boolean acierto = false;
				for (int i = 0; i < palabraAleatoria.length; i++) {
					if (letra == palabraAleatoria[i] && arrayPosiciones[i] == 0) {
						posicionesEnBlanco.remove(i);
						arrayPosiciones[i] = 1;
						acierto = true;
						System.out.println("¡Acertaste una letra sigue asi!");
						break;
					}
				}
				if (posicionesEnBlanco.size() == 0) {
					System.out.println("!!Ganaste la palabra es: " + String.valueOf(palabraAleatoria));
					break;
				}
				if (!acierto) {
					oportunidades--;
					if (oportunidades == 0) {
						System.out.println("Perdiste... la palabra era: " + String.valueOf(palabraAleatoria));
						break;
					} else {
						System.out.println("No acertaste ahora solo tienes " + oportunidades + " oportunidades");
					}
				}
			}
		} while (oportunidades > 0);

	}

	public static String generaPalabraAleatoria() {
		String[] palabras = { "java", "github", "git commit", "git push", "dani", "carmen","fran queremos un 10",
 };
		Random numeros = new Random();
		int aleatorio = numeros.nextInt(palabras.length);
		return palabras[aleatorio];
	}
}
