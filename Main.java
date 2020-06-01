package lession23;

import java.util.Scanner;

/**
 * The Main class to work with iterators
 * @author ARTUR
 * @since JDK 13.0.2
 */
public class Main {
	public static void main(String[] args) throws Exception {
		while (true) {
			switch (printMenu()) {
			case "1": { // створити фракцію
					VRada.ifCreation().addFaction(); 
					break;
			}
			case "2": { // видалити фракцію
					VRada.ifCreation().removeFaction();
					break;
			}
			case "3": {
					VRada.ifCreation().printAllFaction();
					break;
			}
			case "4": {// очистити від депутатів фракцію
					VRada.ifCreation().removeAllDeputatInFaction();
					break;
			}
			case "5": { // надрукувати депутатів вказаної фракції
					VRada.ifCreation().printAllDeputatInFaction();
					break;
			}
			case "6": { // додати депутатів в фракцію
				VRada.ifCreation().addDeputatInFaction(null, null);
				break;
			}
			case "7": { // видалити депутата з фракції
				String nameFaction = VRada.ifCreation().printAllDeputatInFaction();
				VRada.ifCreation().removeDeputatFromFaction(nameFaction);
				break;
			}
			case "8": { // надрукувати список хабарників вказаної фракції
				VRada.ifCreation().printAllDeputatXabarnuk();
				break;
			}
			case "9": { // надрукувати найбільшого хабарника вказаної фракції
				VRada.ifCreation().printBiggestDeputatXabarnukInFraction();
				break;
			}

			case "0": {
				System.exit(0);
			}
			}
		}
	}

	// print menu
	@SuppressWarnings("resource")
	private static String printMenu() {
		System.out.println("Введіть 1  щоб створити фракцію");
		System.out.println("Введіть 2  щоб видалити фракцію");
		System.out.println("Введіть 3  щоб надрукувати всі фракції");
		System.out.println("Введіть 4  щоб очистити від депутатів фракцію");
		System.out.println("Введіть 5  щоб надрукувати депутатів вказаної фракції");
		System.out.println("Введіть 6  щоб додати депутатів в фракцію");
		System.out.println("Введіть 7  щоб видалити депутата з фракції");
		System.out.println("Введіть 8  щоб надрукувати список хабарників вказаної фракції");
		System.out.println("Введіть 9  щоб надрукувати найбільшого хабарника вказаної фракції");
		System.out.println("Введіть 0  щоб вийти з програми");
		return new Scanner(System.in).nextLine();
	}


}
